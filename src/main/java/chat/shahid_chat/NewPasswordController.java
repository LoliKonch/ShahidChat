package chat.shahid_chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewPasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmNewPasswordField;

    @FXML
    private PasswordField secretCodeField;

    @FXML
    private Button setNewPasswordButton;

    @FXML
    void initialize() {
        setNewPasswordButton.setOnAction(event ->{
            if (newPasswordField.getText() != null && !newPasswordField.getText().trim().isEmpty() && confirmNewPasswordField.getText() != null && !confirmNewPasswordField.getText().trim().isEmpty() && secretCodeField.getText() != null && !secretCodeField.getText().trim().isEmpty() && newPasswordField.getText().equals(confirmNewPasswordField.getText())) {
                Stage stage = (Stage) setNewPasswordButton.getScene().getWindow();
                stage.close();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Sign_in.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Parent root = loader.getRoot();
                stage.setScene(new Scene(root));
                stage.setTitle("Shahid Chat №1");
                stage.setResizable(false);
                stage.show();
            }
        });


        backButton.setOnAction(event ->{
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Sign_in.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.setTitle("Shahid Chat №1");
            stage.setResizable(false);
            stage.show();
        });
    }

}
