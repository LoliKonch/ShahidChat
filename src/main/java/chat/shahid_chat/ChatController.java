package chat.shahid_chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChatController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button exitButton;

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

    @FXML
    private VBox vBoxMenu;

    @FXML
    private Pane menuTrigger;

    @FXML
    private RadioButton radioButton1;

    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;

    @FXML
    private RadioButton radioButton4;

    @FXML
    private RadioButton radioButton5;

    @FXML
    private RadioButton radioButton6;

    @FXML
    private RadioButton radioButton7;

    @FXML
    private RadioButton radioButton8;

    @FXML
    private RadioButton radioButton9;

    @FXML
    private RadioButton radioButton10;

    @FXML
    private Button applyThemeButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainBackground.setStyle(String.format(
                "-fx-background-color: %s;",
                ColorPalettes.palette[9])
        );


        scrollPane.setStyle(String.format(
                "-fx-background: %s;",
                ColorPalettes.palette[10])
        );


        vBoxWithMassages.setStyle(String.format(
                "-fx-background-color: %s;",
                ColorPalettes.palette[11])
        );


        vBoxMenu.setStyle(String.format(
                "-fx-background-color: %s;",
                ColorPalettes.palette[12])
        );


        menuTrigger.setStyle(String.format(
                "-fx-background-color: %s;",
                ColorPalettes.palette[13])
        );


        massageField.setStyle(String.format(
                "-fx-border-radius: 5;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: -1;" +
                "-fx-background-radius: 5;" +
                "-fx-background-color: %s;" +
                "-fx-border-color: %s;" +
                "-fx-text-inner-color: %s;",
                ColorPalettes.palette[14],
                ColorPalettes.palette[15],
                ColorPalettes.palette[16])
        );


        sendMassageButton.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[17],
                ColorPalettes.palette[18])
        );


        sendFileButton.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[17],
                ColorPalettes.palette[18])
        );


        exitButton.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[19],
                ColorPalettes.palette[20])
        );


        applyThemeButton.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[19],
                ColorPalettes.palette[20])
        );



        ToggleGroup rbGroupPalettes = new ToggleGroup();

        radioButton1.setToggleGroup(rbGroupPalettes);
        radioButton2.setToggleGroup(rbGroupPalettes);
        radioButton3.setToggleGroup(rbGroupPalettes);
        radioButton4.setToggleGroup(rbGroupPalettes);
        radioButton5.setToggleGroup(rbGroupPalettes);
        radioButton6.setToggleGroup(rbGroupPalettes);
        radioButton7.setToggleGroup(rbGroupPalettes);
        radioButton8.setToggleGroup(rbGroupPalettes);
        radioButton9.setToggleGroup(rbGroupPalettes);
        radioButton10.setToggleGroup(rbGroupPalettes);




        vBoxMenu.setTranslateX(-160);
        TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), vBoxMenu);

        menuTranslation.setFromX(-160);
        menuTranslation.setToX(0);



        menuTrigger.setOnMouseEntered(evt -> {
            menuTranslation.setRate(1);
            menuTranslation.play();
        });
        vBoxMenu.setOnMouseExited(evt -> {
            menuTranslation.setRate(-1);
            menuTranslation.play();
        });




        applyThemeButton.setOnAction(event -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();

            RadioButton selection = (RadioButton) rbGroupPalettes.getSelectedToggle();
            switch (selection.getId()) {
                case "radioButton1" -> ColorPalettes.palette = ColorPalettes.STANDARD;
                case "radiobutton2" -> ColorPalettes.palette = ColorPalettes.DARK_DS;
                case "radiobutton3" -> ColorPalettes.palette = ColorPalettes.LIGHT_VK;
                case "radiobutton4" -> ColorPalettes.palette = ColorPalettes.DARK_VK;
                case "radiobutton5" -> ColorPalettes.palette = ColorPalettes.LIGHT_TG;
                case "radiobutton6" -> ColorPalettes.palette = ColorPalettes.DARK_TG;
                case "radiobutton7" -> ColorPalettes.palette = ColorPalettes.DARK_TROVO;
                case "radiobutton8" -> ColorPalettes.palette = ColorPalettes.BOOSTY;
                case "radiobutton9" -> ColorPalettes.palette = ColorPalettes.DARWIN_TV;
                case "radiobutton10" -> ColorPalettes.palette = ColorPalettes.RAINBOW;
            }

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
        });


        exitButton.setOnAction(event ->{
            Stage stage = (Stage) exitButton.getScene().getWindow();
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

