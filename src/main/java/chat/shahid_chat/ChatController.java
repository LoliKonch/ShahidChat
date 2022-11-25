package chat.shahid_chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button backButton;

    @FXML
    private Button sendMassageButton;

    @FXML
    private Button sendFileButton;

    @FXML
    private TextField massageField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBoxWithMassages;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backButton.setStyle(String.format("-fx-border-color: #000000; -fx-border-radius: 5; -fx-background-color: %s;", ColorPalettes.palette[0]));









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

