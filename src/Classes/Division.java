package Classes;

/**
 * An object defining the different "sections" for the pace. Each division has a specified goal time that all members
 * of that division aim for.
 * <p>A division without a specified goal can be considered <i>"Expendable"</i>, meaning that in the case that there
 * are no more members in that division, the division can be safely deleted.
 * </p>
 */
public class Division {

	private String name = "";
	private TimeStamp goalTime;

	/**
	 * Creates an empty {@link Division} object
	 */
	public Division() {
	}

	/**
	 * Creates a {@link Division} object under a specified {@code name}
	 *
	 * @param name Name of the new {@link Division} object
	 */
	public Division(String name) {
		this.name = name;
	}

	/**
	 * @return Name of the division
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the division
	 * <p>This will not change any team's division, meaning that if there are any teams under the old division name,
	 * a new division under the old division name will be created once the divisions are refreshed.
	 * </p>
	 *
	 * @param name New name for the division
	 * @return Updated {@link Division} object with the modified name
	 */
	public Division setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return The goal time of the division
	 */
	public TimeStamp getGoalTime() {
		return goalTime;
	}

	/**
	 * Sets the goal time of this division.
	 * <p>This is the goal that all members of the division aim for, and ranking is based upon who got closest to
	 * this goal time</p>
	 * <p>Assigning {@code goalTime} to {@code null} will result in "marking" the {@link Division} as expendable,
	 * meaning that if no more members in the division exist, then the division can be safely deleted from the list.</p>
	 *
	 * @param goalTime New goal time
	 * @return Updated {@link Division} object with the modified goalTime
	 */
	public Division setGoalTime(TimeStamp goalTime) {
		this.goalTime = goalTime;
		return this;
	}

	/**
	 * Returns whether or not the {@code goalTime} value is set to null. This is what tells the
	 * {@link Pace#updateDivisions()} whether or not the division can be safely deleted, given no more members of the
	 * pace fall under this division
	 *
	 * @return {@code true} if {@code goalTime} is {@code null} <br> {@code false} if {@code goalTime} is not {@code
	 * null}
	 * @see #getGoalTime()
	 * @see #setGoalTime(TimeStamp)
	 */
	public boolean isGoalNull() {
		return goalTime == null;
	}
}
