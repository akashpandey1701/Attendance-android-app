package com.example.swanapp.admin;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.swanapp.R;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createFragment extends Fragment {

    private EditText id, username, email, password, district, latitude, longitude, roles;
    private Button submit;

    public createFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        // Initialize your EditText fields and Button
        id = view.findViewById(R.id.id);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        district = view.findViewById(R.id.district);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);
        roles = view.findViewById(R.id.role);
        submit = view.findViewById(R.id.submit);

        // Set click listener for submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to create user
                createUser();
            }
        });

        return view;
    }

    private void createUser() {
        // Extract data from EditText fields
        String userId = id.getText().toString().trim();
        String userUsername = username.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String userDistrict = district.getText().toString().trim();
        double userLatitude = Double.parseDouble(latitude.getText().toString().trim());
        double userLongitude = Double.parseDouble(longitude.getText().toString().trim());
        List<String> userRoles = Arrays.asList(roles.getText().toString().trim().split("\\s*,\\s*"));

        // Create user model object
        createUserModel user = new createUserModel(userId, userUsername, userEmail, userPassword, userDistrict, userLatitude, userLongitude, userRoles);

        // Get Retrofit service interface instance
        createUserInterface service = CreateUserRetrofitClientInstance.getRetrofitInstance(getActivity().getApplicationContext()).create(createUserInterface.class);

        // Make API call to create user
        Call<Void> call = service.createUser(user);

        // Enqueue the call asynchronously
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "User created successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Handle other response codes like 401, 403, etc.
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "Failed to create user", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}