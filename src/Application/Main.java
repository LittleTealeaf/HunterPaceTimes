package Application;

import Tools.Json;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        Json.load();
        Settings.load();


        launch(args);
    }

    public static void test() {
////        Pace pace = new Pace();
////        pace.addTeam(new Team("202").setDivision("Western").setNames(new String[] {"Person A","Person B"}));
////        pace.addTeam(new Team("205").setDivision("Junior"));
////        pace.save();
//        Pace pace = Pace.openPace();
    }

    public void start(Stage stage) {
        Main.stage = stage;

        Scene scene = new Scene(new BorderPane());


        stage.setScene(scene);

        stage.show();
        test();
    }
}
