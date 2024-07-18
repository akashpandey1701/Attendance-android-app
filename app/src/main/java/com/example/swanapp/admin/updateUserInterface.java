package com.example.swanapp.admin;

import com.example.swanapp.admin.updateUserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface updateUserInterface {
    @PUT("admin/employee/{id}")
    Call<Void> updateUser(@Path("id") String id, @Body updateUserModel user);
}
