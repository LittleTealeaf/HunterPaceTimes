package Classes;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamNumber;
    private String division = "None";
    private String[] names = new String[] {""};
    private String[] notes = new String[] {""};
    private TimeStamp start;
    private TimeStamp finish;
    private boolean excluded = false;

    /**
     * @apiNote In order to set different values, use functions to set each individual value, they will return the new Team object
     * @param teamNumber Number of the Team
     */
    public Team(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public Team setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
        return this;
    }

    public String getDivision() {
        return division;
    }

    public Team setDivision(String division) {
        this.division = division;
        return this;
    }

    public String[] getNames() {
        return names;
    }

    public Team setNames(String[] names) {
        this.names = names;
        return this;
    }

    public String[] getNotes() {
        return notes;
    }

    public Team setNotes(String[] notes) {
        this.notes = notes;
        return this;
    }

    public TimeStamp getStart() {
        return start;
    }

    public Team setStart(TimeStamp start) {
        this.start = start;
        return this;
    }

    public TimeStamp getFinish() {
        return finish;
    }

    public Team setFinish(TimeStamp finish) {
        this.finish = finish;
        return this;
    }

    public boolean isExcluded() {
        return excluded;
    }

    public Team setExcluded(boolean excluded) {
        this.excluded = excluded;
        return this;
    }



}
