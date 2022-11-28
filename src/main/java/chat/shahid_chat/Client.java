package chat.shahid_chat;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private String username;
    private String password;
    private String email;
    private PublicKey clientPublicKey;
    private PrivateKey clientPrivateKey;
    private PublicKey serverPublicKey;

    public Client(Socket socket, String username, String email, String password) {
        this.socket = socket;
        this.username = username;
        this.email = email;
        this.password = password;

        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            closeEverything();
        }

        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            closeEverything();
        }


        try {
            // создание публичного и приватного ключей клиента
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            clientPrivateKey = keyPair.getPrivate();
            clientPublicKey = keyPair.getPublic();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Ошибка создания ключей клиента!");
        }

    }

    public void startClient() {

        try {
            // отправка публичного ключа клиента
            objectOutputStream.writeObject(clientPublicKey);
            objectOutputStream.flush();

            // получение публичного ключа сервера
            serverPublicKey = (PublicKey) objectInputStream.readObject();

        } catch (Exception e) {
            System.out.println("Ошибка обмена ключами: "+ e);
        }

        try {
            bufferedWriter.write(encryptMessage(username));
            bufferedWriter.newLine();
            bufferedWriter.flush();

            bufferedWriter.write(encryptMessage(email));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Ошибка отправки имени!");
        }


        listenForMessage();
        sendMessage();
    }

    public void sendMessage() {

        try {

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {

                String messageToSend = scanner.nextLine();

                bufferedWriter.write(encryptMessage(messageToSend));
                bufferedWriter.newLine();
                bufferedWriter.flush();

            }

        } catch (IOException e) {
            closeEverything();
            System.out.println("Ошибка отправки сообщения");
        }

    }

    public void listenForMessage() {
        // создание и запуск нового потока для получения сообщений
        new Thread(new Runnable() { // реализация анонимного класса
            @Override
            public void run() {

                String messageFromChat;

                while (socket.isConnected()) {
                    try {

                        messageFromChat = bufferedReader.readLine();
                        System.out.println(decryptMessage(messageFromChat));

                    } catch (IOException e) {
                        closeEverything();
                    }
                }

            }
        }).start();
    }

    private String encryptMessage(String messageToEncrypt) {
        if (serverPublicKey == null)
            throw new RuntimeException("Неизвестный ключ сервера!");

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, serverPublicKey);

            byte[] messageToEncryptBytes = messageToEncrypt.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedMessage = cipher.doFinal(messageToEncryptBytes);

            return Base64.getEncoder().encodeToString(encryptedMessage);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private String decryptMessage(String messageToDecrypt) {
        byte[] messageToDecryptBytes = Base64.getDecoder().decode(messageToDecrypt);

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, clientPrivateKey);

            byte[] decryptedMessage = cipher.doFinal(messageToDecryptBytes);

            return new String(decryptedMessage, StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeEverything() {

        try {

            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
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

    public static void main(String[] args) throws IOException {

//        Scanner scanner = new Scanner(System.in);
//        String username = null;
//        String email = null;
//        boolean flag = true;
//
//        while (flag) {
//            System.out.println("Введите свой ник для чата: ");
//            username = scanner.nextLine();
//
//            System.out.println("Введите email: ");
//            email = scanner.nextLine();
//
//            if (username != null && !username.equals("")) {
//                flag = false;
//            }
//        }
//
//
//        Socket socket = new Socket("localhost", 9090);
//        Client client = new Client(socket, username, email);
//        client.startClient();
    }
}
