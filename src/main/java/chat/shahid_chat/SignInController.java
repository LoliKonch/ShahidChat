package chat.shahid_chat;

import java.io.IOException;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class SignInController {

    private final ChangeWindow  ChangeWindow= new ChangeWindow();

    private static final Pattern LOGIN_PATTERN = Pattern.compile("[a-zA-Z0-9_]");

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registrationButton;

    @FXML
    private Pane sideBackground;

    @FXML
    private Button signInButton;

    @FXML
    void initialize() {

        TextFieldLimiter.addTextLimiter(passwordField, 40);
        TextFieldLimiter.addTextLimiter(loginField, 40);


        registrationButton.setOnAction(event ->
            ChangeWindow.changeWindowTo(sideBackground, "Sign_up.fxml"));


        forgotPassword.setOnAction(event ->
            ChangeWindow.changeWindowTo(sideBackground, "Forgot_your_password.fxml"));


        signInButton.setOnAction(event ->{

            if (loginField.getText() != null && !loginField.getText().trim().isEmpty() &&
                passwordField.getText() != null && !passwordField.getText().trim().isEmpty()) {

                if (LOGIN_PATTERN.matcher(loginField.getText()).find()) {

                    Client.setUsername(loginField.getText().trim());
                    Client.setPassword(passwordField.getText().trim());

                    try {
                        Client.startClient();

                        Client.sendMessage(Client.getUsername());
                        Client.sendMessage("sign_in" +
                                "|" + Client.getUsername() +
                                "|" + Client.getPassword());

                        String answer = Client.waitMessage();
                        if (answer.equals("successful_sign_in")) {
                            ChangeWindow.changeWindowTo(sideBackground, "Chat.fxml");

                        } else {
                            ExceptionBox.createExceptionBox(sideBackground,
                                    "         Incorrect login or password");
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