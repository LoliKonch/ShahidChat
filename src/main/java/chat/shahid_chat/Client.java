package chat.shahid_chat;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {

    private static Socket socket;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;
    private static PGP pgp;
    private static String serverName;
    private static String username;
    private static String password;
    private static String email;
    private static String clientPublicKey;
    private static String clientPrivateKey;
    private static String serverPublicKey;

    public static void startClient() throws IOException {
        socket = new Socket("25.55.56.77", 9090);

        // создание криптографера pgp и сохранение ключей в файл и в переменные
        pgp = new PGP(username);
        clientPublicKey = getStringFromFile(pgp.getPublicKeyFilepath(username));
        clientPrivateKey = getStringFromFile(pgp.getPrivateKeyFilepath(username));
        serverName = pgp.generateSecretCode(15);

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
            writeStringToFile(serverPublicKey, serverName);

        } catch (Exception e) {
            System.err.println("Ошибка обмена ключами: "+ e);
        }

    }

    public static void sendMessage(String messageToSend){

        try {
            objectOutputStream.writeObject(pgp.encryptString(messageToSend, serverName));
            objectOutputStream.flush();
        } catch (IOException e) {
            System.err.println("Ошибка отправки сообщения: " + e);
        }
    }

    public static String waitMessage() {
        try {
            return pgp.decryptString((String) objectInputStream.readObject(), username);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка получения сообщения: " + e);
        }
        return "";
    }

    public static void receiveMessage(VBox vBox) {
        new Thread(new Runnable() { // реализация анонимного класса
            @Override
            public void run() {

                String messageFromServer;

                while (socket.isConnected()) {
                    try {

                        messageFromServer = pgp.decryptString((String) objectInputStream.readObject(), username);
                        String[] messageFromServerList = messageFromServer.split("\\|", 3);
                        if (messageFromServerList[1].equals(getUsername())) {
                            ChatController.displayYourMessageFromServer(messageFromServerList, vBox);
                        }else {
                            ChatController.displayOtherMessage(messageFromServerList, vBox);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        closeEverything();
                        return;
                    }
                }
            }
        }).start();
    }

    private static String getStringFromFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e);
        }
        return null;
    }

    private static void writeStringToFile(String str, String username) {
        try {
            String path = PGP.defaultKeysFilepath + "PublicKey_" + username + ".pgp";
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

    public static String getUsername() {
        return username;
    }

    public static void setPassword(String newPassword) {
        password = newPassword;
    }

    public static String getPassword() {
        return password;
    }

    public static void setEmail(String newEmail) {
        email = newEmail;
    }

    public static String getEmail() {
        return email;
    }

    public static String getClientPublicKey() {
        return clientPublicKey;
    }

    public static void closeEverything() {

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
