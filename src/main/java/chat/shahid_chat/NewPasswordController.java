package chat.shahid_chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewPasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private PasswordField confirmNewPasswordField;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField secretCodeField;

    @FXML
    private Button setNewPasswordButton;

    @FXML
    private Pane sideBackground;

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


        secretCodeField.setStyle(String.format(
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
        TextFieldLimiter.addTextLimiter(secretCodeField, 20);


        newPasswordField.setStyle(String.format(
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
        TextFieldLimiter.addTextLimiter(newPasswordField, 40);


        confirmNewPasswordField.setStyle(String.format(
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
        TextFieldLimiter.addTextLimiter(confirmNewPasswordField, 40);


        setNewPasswordButton.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[2],
                ColorPalettes.palette[5])
        );


        backButton.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[2],
                ColorPalettes.palette[5])
        );


        setNewPasswordButton.setOnAction(event ->{

            if (newPasswordField.getText() != null && !newPasswordField.getText().trim().isEmpty() &&
                confirmNewPasswordField.getText() != null && !confirmNewPasswordField.getText().trim().isEmpty() &&
                secretCodeField.getText() != null && !secretCodeField.getText().trim().isEmpty()) {

                if (newPasswordField.getText().equals(confirmNewPasswordField.getText())) {

                    Client.sendMessage("new_password +" +
                            "|" + Client.getUsername() +
                            "|" + confirmNewPasswordField.getText().trim() +
                            "|" + secretCodeField.getText().trim());


                    String answer = Client.waitMessage();
                    if (answer.equals("successful_password_recovery")) {

                        Stage lastStage = (Stage) setNewPasswordButton.getScene().getWindow();
                        try {

                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("Sign_in.fxml"));
                            loader.load();

                            Stage newStage = new Stage();
                            Parent root = loader.getRoot();
                            newStage.setScene(new Scene(root));
                            newStage.setTitle("Shahid Chat №1");
                            newStage.setResizable(false);
                            newStage.show();

                            lastStage.close();
                        } catch (IOException e) {
                            ExceptionBox.createExceptionBox(sideBackground,
                                    "Can not find required system file");
                        }
                    } else {
                        ExceptionBox.createExceptionBox(sideBackground,
                                "                 Invalid secret code");
                    }

                } else {
                    ExceptionBox.createExceptionBox(sideBackground, "             Passwords must match");
                }
            } else {
                ExceptionBox.createExceptionBox(sideBackground, "          All fields must be filled in");
            }
        });


        backButton.setOnAction(event ->{
            Stage lastStage = (Stage) backButton.getScene().getWindow();
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Sign_in.fxml"));
                loader.load();

                Stage newStage = new Stage();
                Parent root = loader.getRoot();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Shahid Chat №1");
                newStage.setResizable(false);
                newStage.show();

                lastStage.close();
                Client.closeEverything();
            } catch (IOException e) {
                ExceptionBox.createExceptionBox(sideBackground, "Can not find required system file");
            }
        });
    }
}
