package com.example.swanapp.models;

public class datamodel {
    private String email;

    private String name;
    private String password;
    private String designation;
    private double latitude;
    private double longitude;
    private String district;

    public datamodel(String email, String name, String password, String designation, String district, double latitude, double longitude) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.designation = designation;
        this.district = district;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
