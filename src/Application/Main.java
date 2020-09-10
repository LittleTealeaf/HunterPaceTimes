package Application;

import Tools.Json;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    private static int saveRequests = 0;
    private static final Thread settingsThread = new Thread() {
        public void run() {
            try {
                while (saveRequests != -1) {
                    System.out.println("Starting Settings Thread");
                    while (saveRequests == 0) {
                        sleep(1000);
                    }
                    System.out.println("Detected Change");
                    while (saveRequests > 0) {
                        sleep(500);
                        saveRequests--;
                    }
                    Settings.save();
                }
                System.out.println("Escaped");
            } catch(Exception e) {}
        }
    };

    public static void main(String[] args) {
        Json.load();
        Settings.load();
        settingsThread.start();

        launch(args);
    }

    public static void test() {
    }

    private static void updateDimSettings() {
        Settings.ApplicationDisplay.height = stage.getHeight();
        Settings.ApplicationDisplay.width = stage.getWidth();
        Settings.ApplicationDisplay.fullscreen = stage.isFullScreen();
        saveSettings();
    }

    public static void saveSettings() {
        saveRequests = 2;
    }

    public void start(Stage stage) {
        Main.stage = stage;

        Scene scene = new Scene(new BorderPane());

        stage.setOnCloseRequest(e -> {
            saveRequests = -1;
        });

        stage.setHeight(Settings.ApplicationDisplay.height);
        stage.setWidth(Settings.ApplicationDisplay.width);
        stage.setFullScreen(Settings.ApplicationDisplay.fullscreen);
        stage.heightProperty().addListener((e, o, n) -> updateDimSettings());
        stage.widthProperty().addListener((e, o, n) -> updateDimSettings());
        stage.maximizedProperty().addListener((e, o, n) -> updateDimSettings());


        stage.setScene(scene);
        stage.show();
        test();
    }
}
