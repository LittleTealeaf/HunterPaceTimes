package Classes;

public class TimeStamp {
    private int hour;
    private int minute;
    private double second;

    public TimeStamp(double timeValue) {
        this.hour = (int) (timeValue / 3600) ;
        this.minute = (int) ((timeValue - hour * 3600) / 60);
        this.second = timeValue - hour * 3600 - minute * 60;
        System.out.println(hour + " " + minute + " " + second);
    }

    public double getTimeValue() {
        return hour * 3600 + minute * 60 + second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public double getSecond() {
        return second;
    }
}
