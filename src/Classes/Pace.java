package Classes;

import Application.Main;
import Tools.Json;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Pace {

	private static final String fileExtension = ".pace";

	private List<Division> divisions = new ArrayList<>();
	private List<Team> teams = new ArrayList<>();
	private transient File file;

	public Pace() {

	}

	/**
	 * Creates a Pace from a given file. Nothing will import if it fails or if file is non existant. TODO elaborate
	 *
	 * @param file
	 */
	public Pace(File file) {
		this.file = file;
		if (file.exists()) try {
			Pace copy = (Pace) Json.deserialize(new BufferedReader(new FileReader(file)), false, Pace.class);
			this.divisions = copy.divisions;
			this.teams = copy.teams;
		} catch (Exception ignored) {
		}
	}

	public static Pace openPace() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Pace");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pace", "*.pace"));
		return new Pace(fileChooser.showOpenDialog(Main.stage));
	}

	public File getFile() { // unsure if need
		return file;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams() {
		this.teams = teams;
	}

	public void addTeam(Team team) {
		teams.add(team);
	}

	public void removeTeam(Team team) {
		teams.remove(team);
	}

	/**
	 * Updates the division list, removing old divisions and adding new ones according to the teams list
	 *
	 * <p>First goes through and identifies any new divisions, and counts the uses for each of the current divisions.</p>
	 * <p>Then, it marks each division with the following conditions for deletion</p>
	 * <ul><li>No current {@code Team teams} in the list have this division</li>
	 * <li>The division has no goal time set. <i>This is to prevent deletion of manually coded divisions</i></li></ul>
	 * <p>After identifying divisions to remove and add, it removes each division marked for deletion, and adds all new divisions with no goal time</p>
	 */
	public void updateDivisions() {
		//Check to see what divisions are used, and any divisions not included
		int[] members = new int[divisions.size()];
		List<String> newDivisions = new ArrayList<>();
		for (Team team : teams) {
			String division = team.getDivision();
			for (int i = 0; i < divisions.size(); i++) {
				if (division.contentEquals(divisions.get(i).getName())) {
					members[i]++;
					division = null;
				}
			}
			if (division != null) {
				newDivisions.add(division);
			}
		}
		//Check to remove any unused divisions
		List<Division> removeDivisions = new ArrayList<>();
		for (int i = 0; i < members.length; i++) {
			if (members[i] == 0 && divisions.get(i).isGoalNull()) {
				removeDivisions.add(divisions.get(i));
			}
		}
		//Remove divisions
		for (Division division : removeDivisions) {
			divisions.remove(division);
		}
		//Add new divisions
		for (String division : newDivisions) {
			divisions.add(new Division(division));
		}
	}

	public List<Team> getTeamsByStatus(Team.Status status) {
		List<Team> teams = new ArrayList<>();
		for (Team team : this.teams) {
			if (team.getStatus() == status) {
				teams.add(team);
			}
		}
		return teams;
	}

	public void setFile(File file) { //unsure if need
		this.file = file;
	}

	public void save() {
		if (file == null) {
			saveAs();
		} else try {
			FileWriter writer = new FileWriter(file);
			Json.serialize(this, false, writer);
			writer.close();
		} catch (Exception ignore) {
		}
	}

	public void saveAs() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Pace As");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pace", "*.pace"));
		file = fileChooser.showSaveDialog(Main.stage);

		try {
			file.getParentFile().mkdir();
			file.createNewFile();
			save();
		} catch (Exception ignored) {
		} //TODO specify the exception
	}

	public Team[] getPlaces(Division division) {
		//Escape Cases
		if (!divisions.contains(division) || division.getGoalTime() == null) {
			return null;
		}

		//Get all teams in division
		List<Team> divTeams = new ArrayList<>();
		for (Team team : teams) {
			if (team.isDivision(division)) {
				divTeams.add(team);
			}
		}

		//Calculate resultant times
		Team[] places = new Team[divTeams.size()];

		for (int i = 0; i < places.length; i++) {
			TimeStamp closestDifference = null;
			Team closestTeam = null;

			for (Team team : divTeams) {
				TimeStamp difference = team.getElapsedTime().getDifference(division.getGoalTime());
				if (closestTeam == null | difference.isLessThan(closestDifference)) {
					closestTeam = team;
					closestDifference = difference;
				}
			}
			places[i] = closestTeam;
			divTeams.remove(closestTeam);
		}


		return places;
	}
}
