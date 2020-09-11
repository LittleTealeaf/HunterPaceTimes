package Classes;

import Application.Settings;

/**
 * An object containing data pertaining to a specific team.
 * <p>
 * The {@link #excluded} identifier marks whether or not the team should be included in the final scores. If it
 * is set to true, then the team will not show up on any final scores by default.
 * </p>
 */
public class Team {

	private String teamNumber;
	private String division = "None";
	private String[] names = new String[]{""};
	private String[] notes = new String[]{""};
	private TimeStamp start;
	private TimeStamp finish;
	private boolean excluded = false;

	/**
	 * <b>Note</b> In order to set different values, use functions to set each individual value, they will return the new Team object
	 *
	 * @param teamNumber Numerical Identifier of the team, stored as a {@link String}
	 */
	public Team(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	/**
	 * @return Numerical Identifier of the team, stored as a {@link String}
	 */
	public String getTeamNumber() {
		return teamNumber;
	}

	/**
	 * Changes the {@link Team} {@code teamNumber}
	 *
	 * @param teamNumber Numerical Identifier of the team, stored as a {@link String}
	 * @return {@link Team} object with the modified {@code teamNumber}.
	 */
	public Team setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
		return this;
	}

	/**
	 * @return Name Identifier of the Division in which the {@link Team} is classified under
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * Changes the {@link Team} {@code division}
	 *
	 * @param division Name Identifier of the Division in which the {@link Team} is classified under
	 * @return {@link Team} object with the modified {@code division}.
	 */
	public Team setDivision(String division) {
		this.division = division;
		return this;
	}

	/**
	 * @return List of names of each of the team members in a {@link String[] String Array}.
	 */
	public String[] getNames() {
		return names;
	}

	/**
	 * Changes the {@link Team} {@code names}
	 *
	 * @param names List of names of each of the team members in a {@link String[] String Array}.
	 * @return {@link Team} object with the modified {@code names}.
	 */
	public Team setNames(String[] names) {
		this.names = names;
		return this;
	}

	/**
	 * Changes the {@link Team} {@code names}
	 *
	 * @param names List of names of each of the riders separated by line breaks (\n)
	 * @return {@link Team} object with the modified {@code names}.
	 */
	public Team setNames(String names) {
		return setNames(names.split("\n"));
	}

	public String getNamesAsString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < names.length; i++) {
			builder.append(names[i] + (i < names.length - 1 ? "\n" : ""));
		}
		return builder.toString();
	}

	/**
	 * @return the Start time presented as a String for use in places such as the Team Table (found in
	 * {@link Interface.ProgramTeams}
	 */
	public String getStartAsString() {
		return start != null ? start.toString(Settings.displayTwelveHour) : "";
	}

	/**
	 * @return the Finish time presented as a String for use in places such as the Team Table (found in
	 * {@link Interface.ProgramTeams}
	 */
	public String getFinishAsString() {
		return finish != null ? finish.toString(Settings.displayTwelveHour) : "";
	}

	/**
	 * @return the Start time presented as a String for use in places such as the Team Table (found in
	 * {@link Interface.ProgramTeams}
	 */
	public String getElapsedAsString() {
		return getElapsedTime() != null ? getElapsedTime().toString(false) : "";
	}

	/**
	 * @return Notes pertaining to the {@link Team}. Each {@link String} in the array represents a new line (ignoring word wrapping)
	 */
	public String[] getNotes() {
		return notes;
	}

	/**
	 * Changes the {@link Team} {@code notes}
	 *
	 * @param notes Notes pertaining to the {@link Team}. Each {@link String} in the array represents a new line (ignoring word wrapping)
	 * @return {@link Team} object with the modified {@code notes}.
	 */
	public Team setNotes(String[] notes) {
		this.notes = notes;
		return this;
	}

	/**
	 * Converts a single {@code String} variable of notes into a {@code String[]}, and stores that value into the team data
	 *
	 * @param notes a {@code String} pertaining the team notes, lines separated by \n
	 * @return {@link Team} object with the modified {@code notes}.
	 */
	public Team setNotes(String notes) {
		this.notes = notes.split("\\r?\\n");
		return this;
	}

	/**
	 * @return The Starting Time of the team
	 */
	public TimeStamp getStart() {
		return start;
	}

	/**
	 * Changes the {@link Team} {@code start}
	 *
	 * @param start The Starting Time of the team
	 * @return {@link Team} object with the modified {@code start} time.
	 */
	public Team setStart(TimeStamp start) {
		this.start = start;
		return this;
	}

	/**
	 * @return The Finish Time of the team
	 */
	public TimeStamp getFinish() {
		return finish;
	}

	/**
	 * Changes the {@link Team} {@code finish}
	 *
	 * @param finish The Finish Time of the team
	 * @return {@link Team} object with the modified {@code finish} time.
	 */
	public Team setFinish(TimeStamp finish) {
		this.finish = finish;
		return this;
	}

	/**
	 * @return Whether or not the team should be {@code excluded} from the final scores
	 */
	public boolean isExcluded() {
		return excluded;
	}

	/**
	 * Marks whether or not the team should be {@code excluded} from the final scores
	 *
	 * @param excluded Whether or not the team should be {@code excluded} from the final scores
	 * @return {@link Team} object with the modified {@code excluded} value.
	 */
	public Team setExcluded(boolean excluded) {
		this.excluded = excluded;
		return this;
	}

	/**
	 * @return The current status of the team
	 */
	public Status getStatus() {
		return start == null ? Status.IN : finish == null ? Status.OUT : Status.BACK;
	}

	/**
	 * Returns whether or not the {@link Team} is categorized under the given {@link Division}
	 * <p>
	 * This method compares the names without case restrictions, so if the {@link Team} had it's division set as
	 * "{@code HUnt}", this method will return {@code true} if asked to compare it to a {@link Division} named
	 * "{@code hunt}".
	 * </p>
	 *
	 * @param division Division to check if the {@link Team} is part of it
	 * @return {@code True} if the team falls under the given {@link Division}, and {@code False} if it does not.
	 * @see Division
	 */
	public boolean isDivision(Division division) {
		return division.getName().toLowerCase().contentEquals(this.division.toLowerCase());
	}

	/**
	 * @return Elapsed Time between the {@code Start} time and the {@code Finish} time.<br>Returns {@code null} if
	 * either the {@code start} or {@code finish} are null
	 */
	public TimeStamp getElapsedTime() {
		return (finish != null && start != null) ? finish.getDifference(start) : null;
	}

	/**
	 * @return The very first line of the notes<br>In the case that the length of notes is 0 or {@code notes} is
	 * {@code null}, this method will return an empty {@link String}
	 */
	public String getNotesFirstLine() {
		return (notes != null && notes.length > 0) ? notes[0] : "";
	}

	/**
	 * Indicates the current status of a {@link Team}
	 * <p><ul>
	 * <li><b>{@link #IN}</b> - Indicates that the team has not started yet.</li>
	 * <li><b>{@link #OUT}</b> - Indicates that the team is currently in the process of completing the pace</li>
	 * <li><b>{@link #BACK}</b> - Indicates that the team has completed the pace.</li>
	 * </ul></p>
	 */
	enum Status {
		BACK("Back"), IN("In"), OUT("Out");

		private final String display;

		Status(String display) {
			this.display = display;
		}

		public String getDisplay() {
			return display;
		}
	}

}
