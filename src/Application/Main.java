package Application;

import Classes.TimeStamp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {


        TimeStamp time = new TimeStamp(15,9,5);
        System.out.println(time.toString(true));


        launch(args);
    }

    public void start(Stage stage) {

        Scene scene = new Scene(new BorderPane());


        stage.setScene(scene);

        stage.show();
    }
}
