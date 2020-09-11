package Application;

import Tools.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * A collected list of all application settings for the program. This class is saved into a {@link Json} file upon
 * start up, and whenever {@link Main#saveSettings()} is called. {@link #save()} should <b>not</b> be called from
 * anywhere except for {@link Main#saveSettings()} so that the application doesn't save the file too much in a given
 * timeframe.
 *
 * @see Json
 */
public class Settings {

	private static ApplicationDisplay applicationDisplay = new ApplicationDisplay();
	/**
	 * Whether or not the program should display crash reports (TODO implement)
	 */
	public static boolean showCrashReports = true;
	/**
	 * Whether or not to update divisions whenever the user edits the teams (TODO implement)
	 */
	public static boolean updateDivisionsOnTeamsEdit = true;
	/**
	 * A list of recent files opened by the user, specifically for use in the {@link Interface.Welcome Welcome}
	 * interface
	 */
	public static List<String> recentFiles = new ArrayList<>();
	/**
	 * Whether or not {@link Classes.TimeStamp TimeStamp} objects should display as 12 hour times when appropriate
	 */
	public static boolean displayTwelveHour = true;

	public Settings() {
	}

	/**
	 * Initial Loading of the settings file. This will read the file using the {@link Json} tool, converting it to an
	 * object, and assigning the setting values. After initial loading, it will save the file in order to apply any
	 * changes / new settings that the current file does not have
	 */
	public static void load() {
		applicationDisplay = new ApplicationDisplay();
		try {
			Json.readObject(true, Settings.class, "Settings.json");
		} catch (Exception ignore) {
		}
		save();

	}

	/**
	 * Saves the settings into a file using the {@link Json} tool
	 */
	public static void save() {
		Json.saveObject(new Settings(), true, "Settings.json");
	}

	/**
	 * Display settings for the whole application. Contains settings such as the last set height, width, and whether
	 * or not the application is set as fullscreen.
	 */
	public static class ApplicationDisplay {
		public static double height = 400;
		public static double width = 500;
		public static boolean fullscreen = false;

		public ApplicationDisplay() {
		}
	}


}
