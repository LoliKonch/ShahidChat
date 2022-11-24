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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
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


        mainBackground.setStyle(String.format("-fx-background-color: %s;", ColorPalettes.palette[0]));

        sideBackground.setStyle(String.format("-fx-background-radius: 15; -fx-background-color: %s;", ColorPalettes.palette[1]));

        title.setStyle(String.format("-fx-text-fill: %s;", ColorPalettes.palette[4]));

        secretCodeField.setStyle(String.format("-fx-border-radius: 5; -fx-border-width: 2 ;-fx-border-insets: -1; -fx-background-radius: 5; -fx-background-color: %s; -fx-border-color: %s;fx-text-fill: %s;",
                ColorPalettes.palette[3], ColorPalettes.palette[6], ColorPalettes.palette[7]));

        newPasswordField.setStyle(String.format("-fx-border-radius: 5; -fx-border-width: 2 ;-fx-border-insets: -1; -fx-background-radius: 5; -fx-background-color: %s;-fx-border-color: %s;fx-text-fill: %s;",
                ColorPalettes.palette[3], ColorPalettes.palette[6], ColorPalettes.palette[7]));

        confirmNewPasswordField.setStyle(String.format("-fx-border-radius: 5; -fx-border-width: 2 ;-fx-border-insets: -1; -fx-background-radius: 5; -fx-background-color: %s;-fx-border-color: %s;fx-text-fill: %s;",
                ColorPalettes.palette[3], ColorPalettes.palette[6], ColorPalettes.palette[7]));

        setNewPasswordButton.setStyle(String.format("-fx-background-color: %s;", ColorPalettes.palette[2]));
        setNewPasswordButton.setTextFill(Paint.valueOf(ColorPalettes.palette[5]));

        backButton.setStyle(String.format("-fx-background-color: %s;", ColorPalettes.palette[2]));
        backButton.setTextFill(Paint.valueOf(ColorPalettes.palette[5]));


        setNewPasswordButton.setOnAction(event ->{

            if (newPasswordField.getText() != null && !newPasswordField.getText().trim().isEmpty() &&
                    confirmNewPasswordField.getText() != null && !confirmNewPasswordField.getText().trim().isEmpty() &&
                    secretCodeField.getText() != null && !secretCodeField.getText().trim().isEmpty() &&
                    newPasswordField.getText().equals(confirmNewPasswordField.getText())) {

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
