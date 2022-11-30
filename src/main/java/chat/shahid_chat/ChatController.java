package chat.shahid_chat;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
    private Button sendMessageButton;

    @FXML
    private Button sendFileButton;

    @FXML
    private TextField messageField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBoxWithMessages;

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

    private Client client;

    public static void displayMessage(String inMessage, VBox vBox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 10, 5, 5));


        Text text = new Text(inMessage);
        text.setFill(Paint.valueOf(ColorPalettes.palette[29]));
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-background-radius: 20",
                ColorPalettes.palette[24])
        );
        textFlow.setPadding(new Insets(5, 10, 5, 10));


        hBox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        vBoxWithMessages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                scrollPane.setVvalue((Double) newValue);
            }
        });


        Client.receiveMessage(vBoxWithMessages); // сделать перезапуск приложения при смене темы


        mainBackground.setStyle(String.format(
                "-fx-background-color: %s;",
                ColorPalettes.palette[9])
        );


        scrollPane.setStyle(String.format(
                "-fx-background: %s;" +
                "-fx-background-color: %s;",
                ColorPalettes.palette[10],
                ColorPalettes.palette[28])
        );


        vBoxWithMessages.setStyle(String.format(
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


        messageField.setStyle(String.format(
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


        sendMessageButton.setStyle(String.format(
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


        radioButton1.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton1.setOnMouseEntered(evt -> radioButton1.getScene().setCursor(Cursor.HAND));
        radioButton1.setOnMouseExited(evt -> radioButton1.getScene().setCursor(Cursor.DEFAULT));


        radioButton2.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton2.setOnMouseEntered(evt -> radioButton2.getScene().setCursor(Cursor.HAND));
        radioButton2.setOnMouseExited(evt -> radioButton2.getScene().setCursor(Cursor.DEFAULT));


        radioButton3.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton3.setOnMouseEntered(evt -> radioButton3.getScene().setCursor(Cursor.HAND));
        radioButton3.setOnMouseExited(evt -> radioButton3.getScene().setCursor(Cursor.DEFAULT));


        radioButton4.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton4.setOnMouseEntered(evt -> radioButton4.getScene().setCursor(Cursor.HAND));
        radioButton4.setOnMouseExited(evt -> radioButton4.getScene().setCursor(Cursor.DEFAULT));


        radioButton5.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton5.setOnMouseEntered(evt -> radioButton5.getScene().setCursor(Cursor.HAND));
        radioButton5.setOnMouseExited(evt -> radioButton5.getScene().setCursor(Cursor.DEFAULT));


        radioButton6.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton6.setOnMouseEntered(evt -> radioButton6.getScene().setCursor(Cursor.HAND));
        radioButton6.setOnMouseExited(evt -> radioButton6.getScene().setCursor(Cursor.DEFAULT));


        radioButton7.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton7.setOnMouseEntered(evt -> radioButton7.getScene().setCursor(Cursor.HAND));
        radioButton7.setOnMouseExited(evt -> radioButton7.getScene().setCursor(Cursor.DEFAULT));


        radioButton8.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton8.setOnMouseEntered(evt -> radioButton8.getScene().setCursor(Cursor.HAND));
        radioButton8.setOnMouseExited(evt -> radioButton8.getScene().setCursor(Cursor.DEFAULT));


        radioButton9.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton9.setOnMouseEntered(evt -> radioButton9.getScene().setCursor(Cursor.HAND));
        radioButton9.setOnMouseExited(evt -> radioButton9.getScene().setCursor(Cursor.DEFAULT));


        radioButton10.setStyle(String.format(
                "-fx-text-fill: %s;",
                ColorPalettes.palette[22])
        );
        radioButton10.setOnMouseEntered(evt -> radioButton10.getScene().setCursor(Cursor.HAND));
        radioButton10.setOnMouseExited(evt -> radioButton10.getScene().setCursor(Cursor.DEFAULT));

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


        sendMessageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String outMessage = messageField.getText();


                if (!outMessage.isEmpty()) {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5, 5, 5, 10));


                    Text text = new Text(outMessage);
                    text.setFill(Paint.valueOf(ColorPalettes.palette[25]));
                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle(String.format(
                            "-fx-background-color: %s;" +
                            "-fx-background-radius: 20;",
                            ColorPalettes.palette[23])
                    );
                    textFlow.setPadding(new Insets(5, 10, 5, 10));


                    hBox.getChildren().add(textFlow);
                    vBoxWithMessages.getChildren().add(hBox);

                    Client.sendMessage(outMessage);
                    messageField.clear();
                }
            }
        });


        applyThemeButton.setOnAction(event -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();

            RadioButton selection = (RadioButton) rbGroupPalettes.getSelectedToggle();
            switch (selection.getId()) {
                case "radioButton1" -> ColorPalettes.palette = ColorPalettes.STANDARD;
                case "radioButton2" -> ColorPalettes.palette = ColorPalettes.DARK_DS;
                case "radioButton3" -> ColorPalettes.palette = ColorPalettes.LIGHT_VK;
                case "radioButton4" -> ColorPalettes.palette = ColorPalettes.DARK_VK;
                case "radioButton5" -> ColorPalettes.palette = ColorPalettes.LIGHT_TG;
                case "radioButton6" -> ColorPalettes.palette = ColorPalettes.DARK_TG;
                case "radioButton7" -> ColorPalettes.palette = ColorPalettes.DARK_TROVO;
                case "radioButton8" -> ColorPalettes.palette = ColorPalettes.BOOSTY;
                case "radioButton9" -> ColorPalettes.palette = ColorPalettes.DARWIN_TV;
                case "radioButton10" -> ColorPalettes.palette = ColorPalettes.RAINBOW;
            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Chat.fxml"));


            try {
                loader.load();
            } catch (IOException e) {

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

