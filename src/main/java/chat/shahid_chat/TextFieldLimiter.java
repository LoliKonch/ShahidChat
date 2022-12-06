package chat.shahid_chat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class TextFieldLimiter {
    public static void addTextLimiter(TextField textField, int maxLength) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (textField.getText().length() > maxLength) {
                    String textBeforeLimit = textField.getText().substring(0, maxLength);
                    textField.setText(textBeforeLimit);
                }
            }
        });
    }
}
