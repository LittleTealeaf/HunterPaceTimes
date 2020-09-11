package Application;

import Classes.Pace;
import Interface.Program;
import Interface.Welcome;
import Tools.Json;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    public static final String version = "0.0.1-Dev";

    private static int saveRequests = 0;
    private static final Thread settingsThread = new Thread() {
        public void run() {
            try {
                while (saveRequests != -1) {
                    while (saveRequests == 0) {
                        sleep(1000);
                    }
                    int maxWait = 5;
                    while (saveRequests > 0 && maxWait > 0) {
                        sleep(100);
                        maxWait--;
                        saveRequests--;
                    }
                    Settings.save();
                }
            } catch(Exception e) {}
        }
    };

    public static void main(String[] args) {
        Json.load();
        Settings.load();
        settingsThread.start();

        launch(args);
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
	
	public static void openPace(Pace pace) {
        if (pace.getFile() != null) {
            boolean contains = false;
            for (String string : Settings.recentFiles) {
                if (string.contentEquals(pace.getFile().getPath())) contains = true;
            }
            if (!contains) {
                Settings.recentFiles.add(pace.getFile().getPath());
                Settings.save();
            }
        }

        stage.setScene(Program.getInterface(pace));
    }

    public void start(Stage stage) {
        Main.stage = stage;

        Scene scene = new Scene(Welcome.getInterface());

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
    }
}