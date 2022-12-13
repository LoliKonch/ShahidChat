package chat.shahid_chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ForgotYourPasswordController {

    private ChangeWindow  ChangeWindow= new ChangeWindow();

    private static final Pattern LOGIN_PATTERN = Pattern.compile("[a-zA-Z0-9_]");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button confirmLoginButton;

    @FXML
    private TextField loginField;

    @FXML
    private AnchorPane mainBackground;

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


        confirmLoginButton.setStyle(String.format(
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
            ChangeWindow.changeWindowTo(sideBackground, "Sign_in.fxml");
        });


        confirmLoginButton.setOnAction(event ->{
            if (loginField.getText() != null && !loginField.getText().trim().isEmpty()) {

                if (LOGIN_PATTERN.matcher(loginField.getText()).find()) {

                    Client.setUsername(loginField.getText().trim());

                    try {

                        Client.startClient();

                        Client.sendMessage(Client.getUsername());
                        Client.sendMessage("password_recovery" +
                                "|" + Client.getUsername());

                        String answer = Client.waitMessage();
                        if (answer.equals("begin_password_recovery")) {
                            ChangeWindow.changeWindowTo(sideBackground, "New_password.fxml");
                        } else {
                            ExceptionBox.createExceptionBox(sideBackground,
                                    "                   Incorrect login");
                            Client.closeEverything();
                        }

                    } catch (IOException e) {
                        Client.closeEverything();
                        ExceptionBox.createExceptionBox(sideBackground,
                                "        Unable to connect to server" +
                                        "\n         Please try again later");
                    }
                } else {
                    ExceptionBox.createExceptionBox(sideBackground, "                    Invalid Login");
                }
            } else {
                ExceptionBox.createExceptionBox(sideBackground, "          All fields must be filled in");
            }
        });
    }
}