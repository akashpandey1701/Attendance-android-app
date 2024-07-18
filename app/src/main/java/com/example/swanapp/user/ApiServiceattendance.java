package com.example.swanapp.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServiceattendance {
    @POST("/api/user/attendance")
    Call<AttendanceModel> markAttendance(@Body AttendanceModel attendance);

}
