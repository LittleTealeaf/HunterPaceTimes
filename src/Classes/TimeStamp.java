package Classes;

import java.text.DecimalFormat;

public class TimeStamp {
    private int hour;
    private int minute;
    private double second;

    public TimeStamp(double timeValue) {
        setTimeValue(timeValue);
    }

    public TimeStamp(int hour, int minute, double second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public double getTimeValue() {
        return hour * 3600 + minute * 60 + second;
    }

    public void setTimeValue(double timeValue) {
        this.hour = (int) (timeValue / 3600) ;
        this.minute = (int) ((timeValue - hour * 3600) / 60);
        this.second = timeValue - hour * 3600 - minute * 60;
        System.out.println(hour + " " + minute + " " + second);
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

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public String toString() {
        return toString(true);
    }

    public String toString(boolean twelveHour) {
        DecimalFormat integerFormat = new DecimalFormat("00");
        DecimalFormat doubleFormat = new DecimalFormat("00.##");
        if(twelveHour) {
            return integerFormat.format(hour%12) + ":" + integerFormat.format(minute) + ":" + doubleFormat.format(second) + " " + (hour >= 12 ? "PM" : "AM");
        } else {
            return integerFormat.format(hour) + ":" + integerFormat.format(minute) + ":" + doubleFormat.format(second);
        }
    }

    public boolean isBefore(TimeStamp other) {
        return other.getTimeValue() > this.getTimeValue();
    }

    public boolean isAfter(TimeStamp other) {
        return other.getTimeValue() < this.getTimeValue();
    }

    public boolean isEqual(TimeStamp other) {
        return other.getTimeValue() == this.getTimeValue();
    }
}
