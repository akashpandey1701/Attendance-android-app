package com.example.swanapp.admin;

public class VisitRecord {
    private String id;
    private String name;
    private String data;
    private String distance;

    public VisitRecord(String id, String name, String data, String distance) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public String getDistance() {
        return distance;
    }
}
