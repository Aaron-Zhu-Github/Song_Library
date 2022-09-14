/*
 * authors: Ziyun Zhi, Junren Zhu
 */

package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Comparator;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        primaryStage.setTitle("SongLib");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Controller.saveToFile();
            }
        });
    }

}
