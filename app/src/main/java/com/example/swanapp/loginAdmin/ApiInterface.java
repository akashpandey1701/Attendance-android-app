package com.example.swanapp.loginAdmin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("adminLogin")
    Call<JwtResponse> adminLogin(@Body JwtRequest request);
}
