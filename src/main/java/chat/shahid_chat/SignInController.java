package chat.shahid_chat;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
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


        mainBackground.setStyle(String.format("-fx-background-color: %s;", ColorPalettes.palette[0]));

        sideBackground.setStyle(String.format("-fx-background-radius: 15; -fx-background-color: %s;", ColorPalettes.palette[1]));

        title.setStyle(String.format("-fx-text-fill: %s;", ColorPalettes.palette[4]));

        loginField.setStyle(String.format("-fx-border-radius: 5; -fx-background-color: %s; -fx-border-color: %s;fx-text-fill: %s;",
                        ColorPalettes.palette[3], ColorPalettes.palette[6], ColorPalettes.palette[7]));

        passwordField.setStyle(String.format("-fx-border-radius: 5; -fx-background-color: %s;-fx-border-color: %s;fx-text-fill: %s;",
                        ColorPalettes.palette[3], ColorPalettes.palette[6], ColorPalettes.palette[7]));

        registrationButton.setStyle(String.format("-fx-background-color: %s;", ColorPalettes.palette[2]));
        registrationButton.setTextFill(Paint.valueOf(ColorPalettes.palette[5]));

        signInButton.setStyle(String.format("-fx-background-color: %s;", ColorPalettes.palette[2]));
        signInButton.setTextFill(Paint.valueOf(ColorPalettes.palette[5]));

        forgotPassword.setStyle(String.format("-fx-text-fill: %s;", ColorPalettes.palette[8]));


        registrationButton.setOnAction(event ->{
            Stage stage = (Stage) registrationButton.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Sign_up.fxml"));

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


        forgotPassword.setOnAction(event ->{
            Stage stage = (Stage) forgotPassword.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Forgot_your_password.fxml"));

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


        signInButton.setOnAction(event ->{
            if (loginField.getText() != null && !loginField.getText().trim().isEmpty() && passwordField.getText() != null && !passwordField.getText().trim().isEmpty()) {
                Stage stage = (Stage) signInButton.getScene().getWindow();
                stage.close();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Chat.fxml"));

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
    }

}

