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

/**
 * An object containing all data of a given pace. This class contains the list of {@link Division divisions} and
 * {@link Team teams} currently in the pace. Whenever this object is saved or loaded from a file, it uses the
 * {@link Json} tool to
 * convert it
 * to {@code JSON}.
 */
public class Pace {

	private static final String fileExtension = ".pace";

	private final List<Division> divisions = new ArrayList<>();
	private List<Team> teams = new ArrayList<>();
	private transient File file;

	/**
	 * Creates an empty {@link Pace} file
	 */
	public Pace() {

	}

	/**
	 * Opens a pace from a file
	 *
	 * @return Resultant {@link Pace} class extracted from the file
	 */
	public static Pace openPaceFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Pace");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Pace", "*.pace"));
		return fromFile(fileChooser.showOpenDialog(Main.stage));
	}

	/**
	 * Creates a new {@link Pace} object from a given file, setting the {@link #file} variable as the original file
	 * @param file File containing the Json data of the {@link Pace}
	 * @return A new {@link Pace} object with data extracted from the File's Json
	 */
	public static Pace fromFile(File file) {
		if (file != null && file.exists()) try {
			Pace pace = (Pace) Json.deserialize(new BufferedReader(new FileReader(file)), false, Pace.class);
			pace.file = file;
			return pace;
		} catch (Exception ignored) {
		}

		return null;
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

	/**
	 * Returns a list of teams by a certain status
	 *
	 * @param status {@link Team.Status Status} to filter teams by
	 * @return {@link List} of teams who have the specified Status
	 */
	public List<Team> getTeamsByStatus(Team.Status status) {
		List<Team> teams = new ArrayList<>();
		for (Team team : this.teams) {
			if (team.getStatus() == status) {
				teams.add(team);
			}
		}
		return teams;
	}

	/**
	 * Returns only the {@link Team teams} that are not flagged as being excluded from the final scores
	 * @return {@link List} of teams, excluding those marked as excluded from scores
	 */
	public List<Team> getTeamsNotExcluded() {
		List<Team> includedTeams = new ArrayList<Team>();
		for (Team team : teams)
			if (!team.isExcluded()) {
				includedTeams.add(team);
			}
		return includedTeams;
	}

	public void setFile(File file) { //unsure if need
		this.file = file;
	}

	/**
	 * Attempts to save the {@link Pace} to the file specified. If the file does not exist, then it will ask the user
	 * to select a new save location by calling the {@link #saveAs()} method
	 */
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

	/**
	 * Displays a new file chooser interface, asking the user to specify a file name and path to save the
	 * {@link Pace} to
	 * @see #save()
	 */
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

	/**
	 * Returns the places of a specific division
	 *
	 * @param division Specific Division to return the places for
	 * @return {@link Team} array of all teams in that division, in order of closeness to the goal time <p>
	 * Returns {@code Null} if {@code division} is null, if it cannot be found in the current list of divisions,
	 * or if the {@code division} does not have a goal time.
	 * <br>This method will excluded any teams marked with the excluded from final scores tab
	 * </p>
	 */
	public Team[] getPlaces(Division division) {
		return getPlaces(division, true);
	}

	/**
	 * Returns the places of a specific division
	 *
	 * @param division Specific Division to return the places for
	 * @param includeExcluded Whether or not to include teams marked with the {@code excluded from scores} identifier.
	 *                        If this is set to {@code true}, only teams that are not included (by default for
	 *                        new teams) will be added to the places.
	 *                        If set to {@code false}, then all teams, regardless of their exclusion tag, will be
	 *                        included in the scores
	 * @return {@link Team} array of all teams in that division, in order of closeness to the goal time <p>
	 * Returns {@code Null} if {@code division} is null, if it cannot be found in the current list of divisions,
	 * or if the {@code division} does not have a goal time.
	 */
	public Team[] getPlaces(Division division, boolean includeExcluded) {
		//Escape Cases
		if (division == null || !divisions.contains(division) || division.getGoalTime() == null) {
			return null;
		}

		//Get all teams in division
		List<Team> divTeams = new ArrayList<>();
		for (Team team : teams) {
			if (team.isDivision(division) && !(includeExcluded || team.isExcluded())) {
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
