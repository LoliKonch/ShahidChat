package chat.shahid_chat;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


    public static void displayOtherMessage(String inMessage, VBox vBox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 20, 5, 5));

        String[] inMessageList = inMessage.split("\\|", 2);

        VBox messageVBox = new VBox();
        messageVBox.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-background-radius: 15;",
                ColorPalettes.palette[24])
        );


        Label userName = new Label(inMessageList[1]);
        userName.setStyle(String.format(
                "-fx-font-size: 11;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[31]));
        userName.setPadding(new Insets(1, 7, 0, 7));


        Text inMessageText = new Text(inMessageList[2]);
        inMessageText.setFill(Paint.valueOf(ColorPalettes.palette[29]));
        TextFlow inMessageTextFlow = new TextFlow(inMessageText);
        inMessageTextFlow.setPadding(new Insets(0, 10, 0, 5));


        Label dateAndTime = new Label(inMessageList[0]);
        dateAndTime.setStyle(String.format(
                "-fx-font-size: 9;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[33])
        );
        dateAndTime.setPadding(new Insets(0, 7, 1, 7));


        messageVBox.getChildren().add(userName);
        messageVBox.getChildren().add(inMessageTextFlow);
        messageVBox.getChildren().add(dateAndTime);
        hBox.getChildren().add(messageVBox);


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });
    }


    public static void displayYourMessage(String outMessage, VBox vBox, TextField messageField) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5, 5, 5, 20));

        VBox messageVBox = new VBox();
        messageVBox.setStyle(String.format(
                "-fx-background-color: %s;" +
                "-fx-background-radius: 15;",
                ColorPalettes.palette[23])
        );


        Text outMessageText = new Text(outMessage);
        outMessageText.setFill(Paint.valueOf(ColorPalettes.palette[25]));
        TextFlow outMessageTextFlow = new TextFlow(outMessageText);
        outMessageTextFlow.setPadding(new Insets(4, 5, 0, 10));


        Date date = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yy H:mm");
        Label dateAndTime = new Label(formatForDate.format(date));
        dateAndTime.setStyle(String.format(
                "-fx-font-size: 9;" +
                "-fx-text-fill: %s;",
                ColorPalettes.palette[32])
        );
        dateAndTime.setPadding(new Insets(-2, 7, 1, 7));


        messageVBox.getChildren().add(outMessageTextFlow);
        messageVBox.getChildren().add(dateAndTime);
        hBox.getChildren().add(messageVBox);


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
                messageField.clear();
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Client.receiveMessage(vBoxWithMessages);

        vBoxWithMessages.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                scrollPane.setVvalue((Double) newValue);
            }
        });


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

        vBoxMenu.setTranslateX(-160);
        TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), vBoxMenu);

        vBoxMenu.setOnMouseExited(evt -> {
            menuTranslation.setRate(-1);
            menuTranslation.play();
        });


        menuTrigger.setStyle(String.format(
                "-fx-background-color: %s;",
                ColorPalettes.palette[13])
        );

        menuTranslation.setFromX(-160);
        menuTranslation.setToX(0);

        menuTrigger.setOnMouseEntered(evt -> {
            menuTranslation.setRate(1);
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



        sendMessageButton.setOnAction(event -> {
            String outMessage = messageField.getText();

            if (!outMessage.trim().isEmpty()) {
                displayYourMessage(outMessage, vBoxWithMessages, messageField);
                try {
                    Client.sendMessage(messageField.getText());
                } catch (IOException e) {
                    ExceptionBox.createExceptionBox(vBoxWithMessages, "GG рачила криворукий слосмал всё, дибил");
                }
            }
        });


        applyThemeButton.setOnAction(event -> {
            Stage lastStage = (Stage) exitButton.getScene().getWindow();

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
                ExceptionBox.createExceptionBox(vBoxWithMessages, "Can not find required system file");
            }
        });


        exitButton.setOnAction(event ->{
            Stage lastStage = (Stage) exitButton.getScene().getWindow();
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
                ExceptionBox.createExceptionBox(vBoxWithMessages, "Can not find required system file");
            }
        });
    }
}