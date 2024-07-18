package com.example.swanapp.loginUser;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class loginApiclient {


    static String BASE_URL = "http://192.168.139.151:8080/";
        private static Retrofit retrofit;

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

