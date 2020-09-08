package Classes;

public class Division {

	private String name = "";
	private TimeStamp goalTime;

	public Division() {
	}

	public Division(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Division setName(String name) {
		this.name = name;
		return this;
	}

	public TimeStamp getGoalTime() {
		return goalTime;
	}

	public Division setGoalTime(TimeStamp goalTime) {
		this.goalTime = goalTime;
		return this;
	}

	public boolean isGoalNull() {
		return goalTime == null;
	}
}
