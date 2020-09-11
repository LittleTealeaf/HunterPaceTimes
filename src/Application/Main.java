package Application;

import Classes.Pace;
import Interface.Program;
import Interface.Welcome;
import Tools.Json;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static final String version = "0.0.1-Dev";
	public static Stage stage;
	private static int saveRequests = 0;
	/**
	 * A thread that manages when the settings are saved.
	 * <p>
	 * The thread checks every so often to see if {@link #saveRequests} has changed. If it becomes a positive
	 * non-zero number (typically 2 from {@link #saveSettings()}), it then starts a timer. It counts down the
	 * saveRequests to 0 in the case that multiple save requests are made. If either the save request delay
	 * reaches 0, or it has waited the maximum amount of time (500 ms), it will then proceed to save and repeat
	 * the process
	 * </p>
	 * <p>
	 * If at any point {@link #saveRequests} is set to 0, then it will save the settings once more, and then
	 * close itself
	 * </p>
	 */
	private static final Thread settingsThread = new Thread(() -> {
		try {
			while (saveRequests != -1) {
				while (saveRequests == 0) {
					Thread.sleep(1000);
				}
				int maxWait = 5;
				while (saveRequests > 0 && maxWait > 0) {
					Thread.sleep(100);
					maxWait--;
					saveRequests--;
				}
				Settings.save();
			}
		} catch (Exception ignored) {
		}
	});

	public static void main(String[] args) {
		Json.load();
		Settings.load();
		settingsThread.start();

		launch(args);
	}

	/**
	 * Updates the settings page with the current dimension / fullscreen state, and sends a {@link #saveSettings()
	 * Save Request}.
	 * <p>
	 * This function is only to be called whenever the stage's width, height, and fullscreen variables are changed
	 * </p>
	 *
	 * @see #main(String[])
	 * @see #settingsThread
	 * @see #saveSettings()
	 */
	private static void updateDimSettings() {
		Settings.ApplicationDisplay.fullscreen = stage.isFullScreen();
		if (!stage.isFullScreen()) {
			Settings.ApplicationDisplay.height = stage.getHeight();
			Settings.ApplicationDisplay.width = stage.getWidth();
		}
		saveSettings();
	}

	/**
	 * Lets the save thread know that there are changes to {@link Settings} that need to be saved.
	 * <br>This method accomplishes this by setting {@link #saveRequests} to 2, which allows {@link #settingsThread}
	 * to detect the changes and act upon it. This allows for the program to only save after all changes are made to
	 * settings (therefore reducing the number of times the program writes to the settings file)
	 *
	 * @see #settingsThread
	 */
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

		stage.setOnCloseRequest(e -> saveRequests = -1);

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