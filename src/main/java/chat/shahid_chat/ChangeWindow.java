package chat.shahid_chat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeWindow {


    public void changeWindowTo(Pane background, String windowName) {
        Stage lastStage = (Stage) background.getScene().getWindow();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(windowName));
            loader.load();

            Stage newStage = new Stage();
            Parent root = loader.getRoot();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Shahid Chat №1");
            newStage.setResizable(false);
            newStage.show();

            lastStage.close();
        } catch (
                IOException e) {
            ExceptionBox.createExceptionBox(background, "    Can not find required system file");
        }
    }
}
