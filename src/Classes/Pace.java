package Classes;

import java.util.ArrayList;
import java.util.List;

public class Pace {

	private final List<Division> divisions = new ArrayList<>();
	private List<Team> teams = new ArrayList<>();


	public Pace() {
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


}
