
package com.example.swanapp.network;

import com.example.swanapp.admin.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/api/users")
    Call<List<User>> getEmployeeRecords();
}
