package com.example.swanapp.admin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class deleteRetrofitInstance {




        private static Retrofit retrofit;
        private static final String BASE_URL = "http://192.168.139.151:8080/";

        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

