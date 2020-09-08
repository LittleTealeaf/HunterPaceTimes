package Settings;

import Tools.Json;

public class Settings {


	public static User user = new User();
	public static ApplicationSettings appsettings = new ApplicationSettings();

	public static class User {
		public static boolean showCrashReports = true;

		public User() {
		}

		public static boolean updateDivisionsOnTeamsEdit = true;

	}

	public static class ApplicationSettings {
		public static StagePreferences MainStagePref = new StagePreferences();

		public ApplicationSettings() {
		}
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
