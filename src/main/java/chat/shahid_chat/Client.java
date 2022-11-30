package chat.shahid_chat;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private PGP pgp;
    private static String username = "username";
    private static String email;
    private String clientPublicKey;
    private String clientPrivateKey;
    private String serverPublicKey;

    public Client(Socket socket) {
        this.socket = socket;

        // создание сриптографера pgp и сохранение ключей в файл и в переменные
        pgp = new PGP(username);
        clientPublicKey = getStringFromFile(pgp.getPublicKeyFilepath(username));
        clientPrivateKey = getStringFromFile(pgp.getPrivateKeyFilepath(username));

        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            closeEverything();
        }

        try {
            // отправка публичного ключа клиента
            objectOutputStream.writeObject(clientPublicKey);
            objectOutputStream.flush();

            // получение публичного ключа сервера
            serverPublicKey = (String) objectInputStream.readObject();
            writeStringToFile(serverPublicKey, "server");

        } catch (Exception e) {
            System.err.println("Ошибка обмена ключами: "+ e);
        }

    }

    public void sendMessage(String messageToSend) {

        try {

            objectOutputStream.writeObject(pgp.encryptString(messageToSend, "server"));
            objectOutputStream.flush();

        } catch (IOException e) {
            closeEverything();
            System.err.println("Ошибка отправки сообщения");
        }

    }

    public void receiveMessage(VBox vBox) {
        new Thread(new Runnable() { // реализация анонимного класса
            @Override
            public void run() {

                String messageFromServer;

                while (socket.isConnected()) {
                    try {

                        messageFromServer = pgp.decryptString((String) objectInputStream.readObject(), username);
                        System.out.println(messageFromServer); // тестирование
                        ChatController.displayMessage(messageFromServer, vBox);

                    } catch (IOException | ClassNotFoundException e) {
                        closeEverything();
                    }
                }

            }
        }).start();
    }

    private String getStringFromFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e);
        }
        return null;
    }

    private void writeStringToFile(String str, String username) {
        try {
            String path = "src/main/java/chat/shahid_chat/res/PublicKey_" + username + ".pgp";
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.err.println("Ошибка записи ключа в файл: " + e);
        }
    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }

    public static void setEmail(String newEmail) {
        email = newEmail;
    }

    public void closeEverything() {

        try {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (socket != null) {
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
