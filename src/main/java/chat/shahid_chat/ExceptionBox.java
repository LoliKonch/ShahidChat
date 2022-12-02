package chat.shahid_chat;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class ExceptionBox {

    public static void createExceptionBox(Pane background, String exceptionText) {

        Label exceptionLabel = new Label(exceptionText);
        exceptionLabel.setStyle(String.format(
                "-fx-text-fill: red;" +
                "-fx-background-color: %s;" +
                "-fx-background-radius: 25;" +
                "-fx-border-color: red;" +
                "-fx-border-radius: 25;" +
                "-fx-border-insets: -1",
                ColorPalettes.palette[0]));
        exceptionLabel.setPrefSize(200, 50);
        exceptionLabel.setTextAlignment(TextAlignment.CENTER);

        background.getChildren().add(exceptionLabel);
        exceptionLabel.setTranslateX(150);
        exceptionLabel.setTranslateY(400);

        TranslateTransition exLabelTranslationAppear = new TranslateTransition(Duration.millis(90), exceptionLabel);
        exLabelTranslationAppear.setByY(-100);
        exLabelTranslationAppear.play();

        TranslateTransition exLabelTranslationDisappear = new TranslateTransition(Duration.millis(90), exceptionLabel);
        exLabelTranslationDisappear.setByY(100);
        exLabelTranslationDisappear.setDelay(Duration.millis(3000));
        exLabelTranslationDisappear.play();
    }
}
