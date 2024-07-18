package com.example.swanapp.user;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientattendance {

        private static final String BASE_URL = "http://192.168.139.151:8080/";

        private static Retrofit retrofit = null;

        public static Retrofit getClient() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
