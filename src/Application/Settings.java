package Application;

import Tools.Json;

public class Settings {

	public static boolean showCrashReports = true;
	public static boolean updateDivisionsOnTeamsEdit = true;

	public static class ApplicationDisplay {
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
