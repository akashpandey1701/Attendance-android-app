package com.example.swanapp.admin;


import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface deleteUserInterface {
    @DELETE("employee/{id}")
    Call<Void> deleteUser(@Path("id") String id);
}
