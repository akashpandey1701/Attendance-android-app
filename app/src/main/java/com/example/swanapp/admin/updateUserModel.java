package com.example.swanapp.admin;

public class updateUserModel {
    private String email;
    private String name;
    private String password;
    private String district;
    private Double latitude;
    private Double longitude;



    public updateUserModel() {

    }

    public updateUserModel(String email, String name, String password,  String district, Double latitude, Double longitude) {
        this.email = email;
        this.name = name;
        this.password = password;
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



    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
