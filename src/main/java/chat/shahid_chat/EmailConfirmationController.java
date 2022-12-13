package chat.shahid_chat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;

public class EmailConfirmationController {

    private ChangeWindow  ChangeWindow= new ChangeWindow();

    @FXML
    private Button backButton;

    @FXML
    private PasswordField secretCodeField;

    @FXML
    private Button sendConfirmationCodeButton;

    @FXML
    private Pane sideBackground;

    @FXML
    void initialize() {

        TextFieldLimiter.addTextLimiter(secretCodeField, 20);


        sendConfirmationCodeButton.setOnAction(event ->{

            if (secretCodeField.getText() != null && !secretCodeField.getText().trim().isEmpty()){

                String secretCode = secretCodeField.getText();
                Client.sendMessage(secretCode);

                String answer = Client.waitMessage();
                if (answer.equals("successful_sign_up")) {
                    ChangeWindow.changeWindowTo(sideBackground, "Sign_in.fxml", false);
                } else {
                    ExceptionBox.createExceptionBox(sideBackground,
                            "                 Invalid secret code");
                }

            } else {
                ExceptionBox.createExceptionBox(sideBackground,
                        "          All fields must be filled in");
            }
        });


        backButton.setOnAction(event ->{
            ChangeWindow.changeWindowTo(sideBackground, "Sign_in.fxml", false);
        });
    }
}

