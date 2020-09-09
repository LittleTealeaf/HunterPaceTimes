package Application;

import Classes.Pace;
import Interface.Welcome;
import Settings.Settings;
import Tools.Json;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String version = "0.0.1-Dev";

    public static Stage stage;

    public static void main(String[] args) {
        Json.load();
        Settings.load();


        launch(args);
    }

    public static void test() {

    }

    public static void openPace(Pace pace) {
        System.out.println("Opening pace from file " + (pace.getFile() != null ? pace.getFile().getPath() : "null"));
    }

    public void start(Stage stage) {
        Main.stage = stage;
        Settings.ApplicationSettings.MainStagePref.applyPreferences(stage);

        Scene scene = new Scene(Welcome.getInterface());


        stage.setScene(scene);

        stage.show();
        test();
    }
}
