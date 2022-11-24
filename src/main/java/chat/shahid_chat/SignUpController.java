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

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField mailFild;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {
        signUpButton.setOnAction(event ->{
            if (loginField.getText() != null && !loginField.getText().trim().isEmpty() && passwordField.getText() != null && !passwordField.getText().trim().isEmpty() && mailFild.getText() != null && !mailFild.getText().trim().isEmpty()) {
                Stage stage = (Stage) signUpButton.getScene().getWindow();
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
