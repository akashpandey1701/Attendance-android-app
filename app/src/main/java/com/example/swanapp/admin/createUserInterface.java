package com.example.swanapp.admin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface createUserInterface {
    @POST("admin/employee")
    Call<Void> createUser(@Body createUserModel user);
}
