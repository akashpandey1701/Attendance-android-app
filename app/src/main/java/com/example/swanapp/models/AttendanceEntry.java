package com.example.swanapp.models;

public class AttendanceEntry {
    private String clockInTime;
    private String clockOutTime;

    public AttendanceEntry(String clockInTime, String clockOutTime) {
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
    }

    public String getClockInTime() {
        return clockInTime;
    }

    public String getClockOutTime() {
        return clockOutTime;
    }
}
