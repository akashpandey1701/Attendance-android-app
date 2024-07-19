package com.example.swanapp.admin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    @GET("/admin/employee")
    Call<List<User>> getUsers();

    @GET("admin/attendance")
    Call<List<Attendance>> getAttendance();

    @GET("admin/visit")
    Call<List<Visit>> getVisits();
}

