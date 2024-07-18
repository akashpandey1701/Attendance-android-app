package com.example.swanapp.admin;

public class AdminattendanceRecord {
    private String id;
    private String name;
    private String markInTime;
    private String markOutTime;

    public AdminattendanceRecord(String id, String name, String markInTime, String markOutTime) {
        this.id = id;
        this.name = name;
        this.markInTime = markInTime;
        this.markOutTime = markOutTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMarkInTime() {
        return markInTime;
    }

    public String getMarkOutTime() {
        return markOutTime;
    }
}
