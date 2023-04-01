package org.example;

import java.time.DayOfWeek;

public class Timeslot {
    private int timeslotId;
    private DayOfWeek dayOfWeek;
    private String startTime;
    private String endTime;

    public Timeslot(int timeslotId, DayOfWeek dayOfWeek, String startTime, String endTime) {
        this.timeslotId = timeslotId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(int timeslotId) {
        this.timeslotId = timeslotId;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

