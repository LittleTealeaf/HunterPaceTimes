package Application;

import Classes.Pace;
import Interface.OpenPace;
import Settings.Settings;
import Tools.Json;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static Stage stage;

    public static Pace pace;

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

    public static void openPace(File file) {

    }

    public void start(Stage stage) {
        Main.stage = stage;
        Settings.ApplicationSettings.MainStagePref.applyPreferences(stage);

        Scene scene = new Scene(OpenPace.getInterface());


        stage.setScene(scene);

        stage.show();
        test();
    }
}
