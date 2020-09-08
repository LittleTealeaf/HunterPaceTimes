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

    public Team(String teamNumber) {
        this.teamNumber = teamNumber;
    }

}
