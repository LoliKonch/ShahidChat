package chat.shahid_chat;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class ChatController implements Initializable {

    private ChangeWindow  ChangeWindow= new ChangeWindow();
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
    private Button applyThemeButton;


    public static void displayOtherMessage(String[] inMessageList, VBox vBox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 20, 5, 5));

        VBox messageVBox = new VBox();
        messageVBox.getStyleClass().add("other-message-vbox");

        Label userName = new Label(inMessageList[1]);
        userName.getStyleClass().add("user-name");

        Text inMessageText = new Text(inMessageList[2]);
        TextFlow inMessageTextFlow = new TextFlow(inMessageText);
        inMessageTextFlow.getStyleClass().add("in-message-text-flow");

        Label dateAndTime = new Label(inMessageList[0]);
        dateAndTime.getStyleClass().add("other-date-and-time");

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


    public static void displayYourMessage(String[] yourMessage, VBox vBox) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5, 5, 5, 20));

        VBox messageVBox = new VBox();
        messageVBox.getStyleClass().add("your-message-vbox");

        Text yourMessageText = new Text(yourMessage[2]);
        TextFlow yourMessageTextFlow = new TextFlow(yourMessageText);
        yourMessageTextFlow.getStyleClass().add("your-message-text-flow");

        Label dateAndTime = new Label(yourMessage[0]);
        dateAndTime.getStyleClass().add("your-date-and-time");

        messageVBox.getChildren().add(yourMessageTextFlow);
        messageVBox.getChildren().add(dateAndTime);
        hBox.getChildren().add(messageVBox);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
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


        TextFieldLimiter.addTextLimiter(messageField, 5000);

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


        sendMessageButton.setOnAction(event -> {
            String outMessage = messageField.getText();

            if (!outMessage.trim().isEmpty()) {
                Date date = new Date();
                SimpleDateFormat formatForDate = new SimpleDateFormat("dd.MM.yy H:mm");
                String[] message = {formatForDate.format(date), "", outMessage};
                displayYourMessage(message, vBoxWithMessages);
                messageField.clear();
                Client.sendMessage(outMessage);

            }
        });


        applyThemeButton.setOnAction(event -> {

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
            }


            ChangeWindow.changeWindowTo(menuTrigger, "Sign_in.fxml", false);
        });


        exitButton.setOnAction(event ->{
            ChangeWindow.changeWindowTo(menuTrigger, "Sign_in.fxml", false);
        });
    }
}