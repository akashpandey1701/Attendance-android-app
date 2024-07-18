package com.example.swanapp.loginUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface loginApiinterface {
    @POST("userLogin")
    Call<JwtResponse> userLogin(@Body JwtRequest request);


}
