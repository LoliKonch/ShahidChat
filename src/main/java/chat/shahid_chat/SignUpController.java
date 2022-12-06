package chat.shahid_chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
import javafx.stage.Stage;

public class SignUpController {

    private static final Pattern RCF2822 = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                    "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])" +
                    "?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]" +
                    "?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\" +
                    "\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField mailField;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Pane sideBackground;

    @FXML
    private Button signUpButton;

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
        TextFieldLimiter.addTextLimiter(loginField, 40);


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
        TextFieldLimiter.addTextLimiter(passwordField, 40);


        mailField.setStyle(String.format(
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
        TextFieldLimiter.addTextLimiter(mailField, 100);


        signUpButton.setStyle(String.format(
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
            } catch (IOException e) {
                ExceptionBox.createExceptionBox(sideBackground, "Can not find required system file");
            }
        });


        signUpButton.setOnAction(event ->{
            if (loginField.getText() != null && !loginField.getText().trim().isEmpty() &&
                passwordField.getText() != null && !passwordField.getText().trim().isEmpty() &&
                mailField.getText() != null && !mailField.getText().trim().isEmpty()) {

                if (RCF2822.matcher(mailField.getText()).matches()) {

                    Stage lastStage = (Stage) signUpButton.getScene().getWindow();
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
                        ExceptionBox.createExceptionBox(sideBackground, "Can not find required system file");
                    }
                } else {
                    ExceptionBox.createExceptionBox(sideBackground, "Invalid E-mail address");
                }
            } else {
                ExceptionBox.createExceptionBox(sideBackground, "          All fields must be filled in");
            }
        });
    }
}
