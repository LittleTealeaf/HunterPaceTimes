package Application;

import Tools.Json;

import java.util.ArrayList;
import java.util.List;

public class Settings {

	private static final ApplicationDisplay applicationDisplay = new ApplicationDisplay();

	public static boolean showCrashReports = true;
	public static boolean updateDivisionsOnTeamsEdit = true;
	public static List<String> recentFiles = new ArrayList<>();
	public static boolean displayTwelveHour = true;

	public static class ApplicationDisplay {
		public ApplicationDisplay() {
		}

		public static double height = 400;
		public static double width = 500;
		public static boolean fullscreen = false;
	}


	public Settings() {
	}

	public static void load() {

		try {
			Json.readObject(true, Settings.class, "Settings.json");
		} catch (Exception ignore) {
		}
		save();

	}

	public static void save() {
		Json.saveObject(new Settings(), true, "Settings.json");
	}




}
