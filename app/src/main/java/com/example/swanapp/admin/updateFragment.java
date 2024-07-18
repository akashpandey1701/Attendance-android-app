package com.example.swanapp.admin;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.swanapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class updateFragment extends Fragment {

    private EditText userId, email, name, password, district, latitude, longitude;
    private Button updateButton;

    public updateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        // Initialize UI elements
        userId = view.findViewById(R.id.user_id);
        email = view.findViewById(R.id.email);
        name = view.findViewById(R.id.name);
        password = view.findViewById(R.id.password);
        district = view.findViewById(R.id.district);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);
        updateButton = view.findViewById(R.id.update);

        // Set click listener for the update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        return view;
    }

    private void updateUser() {
        // Get user inputs from EditText fields
        String userIdStr = userId.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userName = name.getText().toString().trim();
        String userPassword = password.getText().toString().trim();
        String userDistrict = district.getText().toString().trim();
        String latitudeStr = latitude.getText().toString().trim();
        String longitudeStr = longitude.getText().toString().trim();

        // Validate user ID
        if (userIdStr.isEmpty()) {
            showToast("Please enter User ID");
            return;
        }

        // Create an instance of updateUserModel with updated fields
        updateUserModel user = new updateUserModel();
        if (!userEmail.isEmpty()) {
            user.setEmail(userEmail);
        }
        if (!userName.isEmpty()) {
            user.setName(userName);
        }
        if (!userPassword.isEmpty()) {
            user.setPassword(userPassword);
        }
        if (!userDistrict.isEmpty()) {
            user.setDistrict(userDistrict);
        }
        if (!latitudeStr.isEmpty()) {
            user.setLatitude(Double.parseDouble(latitudeStr));
        }
        if (!longitudeStr.isEmpty()) {
            user.setLongitude(Double.parseDouble(longitudeStr));
        }

        // Create Retrofit instance
        Retrofit retrofit = updateRetrofitClientInterface.getRetrofitInstance();
        updateUserInterface apiService = retrofit.create(updateUserInterface.class);

        // Make API call to update user
        Call<Void> call = apiService.updateUser(userIdStr, user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Update successful
                    showToast("User updated successfully");
                    Log.d("UpdateFragment", "User updated successfully");
                } else {
                    // Update failed
                    showToast("Failed to update user");
                    Log.e("UpdateFragment", "Failed to update user: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Request failed
                showToast("Error: " + t.getMessage());
                Log.e("UpdateFragment", "Error: " + t.getMessage(), t);
            }
        });
    }

    // Helper method to show toast messages
    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show());
    }
}
