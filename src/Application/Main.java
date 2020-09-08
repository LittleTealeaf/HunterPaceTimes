package Application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {


        launch(args);
    }

    public void start(Stage stage) {

        Scene scene = new Scene(new BorderPane());


        stage.setScene(scene);

        stage.show();
    }
}
