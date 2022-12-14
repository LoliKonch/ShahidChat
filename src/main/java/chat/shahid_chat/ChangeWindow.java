package chat.shahid_chat;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ChangeWindow {

    public static String styleName = "dark_DS";

    public void changeWindowTo(Pane background, String windowName, boolean sendBack) {
        Stage lastStage = (Stage) background.getScene().getWindow();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(windowName));
            loader.load();

            Stage newStage = new Stage();
            Parent root = loader.getRoot();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Shahid Chat №1");
            newStage.setResizable(false);
            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    if (sendBack) {
                        Client.sendMessage("back");
                    }
                    System.exit(0);
                }
            });

            Scene scene = newStage.getScene();
            if (windowName.equals("Chat.fxml")) {
                String stylesheet = getClass().getResource("Chat_" + styleName + "_.css").toExternalForm();
                scene.getStylesheets().add(stylesheet);
            } else {
                String stylesheet = getClass().getResource("Registration_" + styleName + "_.css").toExternalForm();
                scene.getStylesheets().add(stylesheet);
            }
            newStage.show();

            lastStage.close();
        } catch (
                IOException e) {
            ExceptionBox.createExceptionBox(background, "    Can not find required system file");
        }
    }
}
