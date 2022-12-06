package chat.shahid_chat;


import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private TextField loginField;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrationButton;

    @FXML
    private Pane sideBackground;

    @FXML
    private Button signInButton;

    @FXML
    private Label title;

    @FXML
    void initialize() {


        mainBackground.setStyle(String.format(
                "-fx-background-color: %s;",
                ColorPalettes.palette[0])
        );


        sideBackground.setStyle(String.format(
                "-fx-background-radius: 15;" +
                "-fx-background-color: %s;",
                ColorPalettes.palette[1])
        );


        title.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[4])
        );


        loginField.setStyle(String.format(
                "-fx-border-radius: 5;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: -1;" +
                "-fx-background-radius: 5;" +
                "-fx-background-color: %s;" +
                "-fx-border-color: %s;" +
                "-fx-text-inner-color: %s;",
                ColorPalettes.palette[3],
                ColorPalettes.palette[6],
                ColorPalettes.palette[7])
        );


        passwordField.setStyle(String.format(
                "-fx-border-radius: 5;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: -1;" +
                "-fx-background-radius: 5;" +
                "-fx-background-color: %s;" +
                "-fx-border-color: %s;" +
                "-fx-text-inner-color: %s;",
                ColorPalettes.palette[3],
                ColorPalettes.palette[6],
                ColorPalettes.palette[7])
        );


        registrationButton.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[2],
                ColorPalettes.palette[5])
        );



        signInButton.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[2],
                ColorPalettes.palette[5])
        );


        forgotPassword.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[8])
        );



        registrationButton.setOnAction(event ->{
            Stage lastStage = (Stage) forgotPassword.getScene().getWindow();
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Sign_up.fxml"));
                loader.load();

                Stage newStage = new Stage();
                Parent root = loader.getRoot();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Shahid Chat №1");
                newStage.setResizable(false);
                newStage.show();

                lastStage.close();
            } catch (IOException e) {
                ExceptionBox.createExceptionBox(sideBackground, "     Can not find required system file");
            }
        });


        forgotPassword.setOnAction(event ->{
            Stage lastStage = (Stage) forgotPassword.getScene().getWindow();
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Forgot_your_password.fxml"));
                loader.load();

                Stage newStage = new Stage();
                Parent root = loader.getRoot();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Shahid Chat №1");
                newStage.setResizable(false);
                newStage.show();

                lastStage.close();
            } catch (IOException e) {
                ExceptionBox.createExceptionBox(sideBackground, "      Can not find required system file");
            }
        });


        signInButton.setOnAction(event ->{

            if (loginField.getText() != null && !loginField.getText().trim().isEmpty() &&
                passwordField.getText() != null && !passwordField.getText().trim().isEmpty()) {

                Client.setUsername(loginField.getText().trim());
                Client.setPassword(passwordField.getText().trim());

                try {
                    Client.startClient(new Socket("localhost", 9090));
                } catch (IOException e) {
                    ExceptionBox.createExceptionBox(sideBackground,
                            "        Unable to connect to server" +
                            "\n         Please try again later");
                }

                try {
                    Client.sendMessage(Client.getUsername());
                    Client.sendMessage(Client.getPassword());
                } catch (IOException e) {
                    ExceptionBox.createExceptionBox(sideBackground,
                            "        Unable to connect to server" +
                            "\n         Please try again later");
                    Client.closeEverything();
                }




                if(true){//ZAgotovOCHKA

                    Stage lastStage = (Stage) forgotPassword.getScene().getWindow();
                    try {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("Chat.fxml"));
                        loader.load();

                        Stage newStage = new Stage();
                        Parent root = loader.getRoot();
                        newStage.setScene(new Scene(root));
                        newStage.setTitle("Shahid Chat №1");
                        newStage.setResizable(false);
                        newStage.show();

                        lastStage.close();
                    } catch (IOException e) {
                        ExceptionBox.createExceptionBox(sideBackground, "Can not find required system file");
                    }
                } else {
                    ExceptionBox.createExceptionBox(sideBackground, "         Incorrect login or password");
                }
            } else {
                ExceptionBox.createExceptionBox(sideBackground, "          All fields must be filled in");
            }
        });
    }
}

