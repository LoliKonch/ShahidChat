package chat.shahid_chat;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class EmailConfirmationController {

    private ChangeWindow  ChangeWindow= new ChangeWindow();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private PasswordField secretCodeField;

    @FXML
    private Button sendConfirmationCodeButton;

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


        sendConfirmationCodeButton.setStyle(String.format(
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

