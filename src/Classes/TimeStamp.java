package Classes;

import java.text.DecimalFormat;

public class TimeStamp {
    private int hour;
    private int minute;
    private double second;

    /**
     * @param timeValue Total number of seconds since 00:00 (12:00 AM)
     */
    public TimeStamp(double timeValue) {
        setTimeValue(timeValue);
    }

    public TimeStamp(int hour, int minute, double second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * @return Total number of seconds since 00:00 (12:00 AM)
     */
    public double getTimeValue() {
        return hour * 3600 + minute * 60 + second;
    }

    /**
     * @param timeValue Total number of seconds since 00:00 (12:00 AM)
     */
    public void setTimeValue(double timeValue) {
        this.hour = (int) (timeValue / 3600);
        this.minute = (int) ((timeValue - hour * 3600) / 60);
        this.second = timeValue - hour * 3600 - minute * 60;
        System.out.println(hour + " " + minute + " " + second);
    }

    /**
     * @return Total number of hours since 00:00 (12:00 AM)
     */
    public int getHour() {
        return hour;
    }

    /**
     * @param hour Total number of hours since 00:00 (12:00 AM)
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * @return Number of minutes since last hour
     */
    public int getMinute() {
        return minute;
    }

    /**
     * @param minute Number of minutes since last hour
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * @return Number of seconds since last minute
     */
    public double getSecond() {
        return second;
    }

    /**
     * @param second Number of seconds since last minute
     */
    public void setSecond(double second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean twelveHour) {
        DecimalFormat integerFormat = new DecimalFormat("00");
        DecimalFormat doubleFormat = new DecimalFormat("00.##");
        if (twelveHour) {
            return integerFormat.format(hour % 12) + ":" + integerFormat.format(minute) + ":" + doubleFormat.format(second) + " " + (hour >= 12 ? "PM" : "AM");
        } else {
            return integerFormat.format(hour) + ":" + integerFormat.format(minute) + ":" + doubleFormat.format(second);
        }
    }


    public boolean isLessThan(TimeStamp other) {
        return other.getTimeValue() > this.getTimeValue();
    }


    public boolean isGreaterThan(TimeStamp other) {
        return other.getTimeValue() < this.getTimeValue();
    }


    public boolean isEqual(TimeStamp other) {
        return other.getTimeValue() == this.getTimeValue();
    }

    public TimeStamp getDifference(TimeStamp other) {
        return new TimeStamp(Math.abs(this.getTimeValue() - other.getTimeValue()));
    }
}
