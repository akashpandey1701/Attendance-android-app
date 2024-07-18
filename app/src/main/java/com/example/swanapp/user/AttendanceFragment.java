package com.example.swanapp.user;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.swanapp.R;
import com.example.swanapp.user.ApiClientattendance;
import com.example.swanapp.user.ApiServiceattendance;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceFragment extends Fragment {

    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST = 1;
    private static final int REQUEST_CHECK_SETTINGS = 2;
    private static final String PREFS_NAME = "AttendancePrefs";
    private static final String MARKED_IN_KEY = "hasMarkedIn";
    private static final String MARK_IN_LAT_KEY = "markInLat";
    private static final String MARK_OUT_LONG_KEY = "markOutLon";
    private static final String MARKED_IN_TIME_KEY = "markInTime";
    private static final String MARK_IN_DATE_KEY = "markInDate";
    private static final String LAST_MARK_OUT_LAT_KEY = "lastMarkOutLat";
    private static final String LAST_MARK_OUT_LON_KEY = "lastMarkOutLon";
    private static final String MARKED_OUT_TIME_KEY = "lastMarkOutTime";
    private static final String LAST_MARK_OUT_DATE_KEY = "lastMarkOutDate";
    private SharedPreferences userPrefs;

    public AttendanceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());
        userPrefs = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        Button markInButton = view.findViewById(R.id.markIn);
        Button markOutButton = view.findViewById(R.id.markOut);

        markInButton.setOnClickListener(v -> checkLocationSettingsAndMarkIn());
        markOutButton.setOnClickListener(v -> checkLocationSettingsAndMarkOut());

        return view;
    }

    private void checkLocationSettingsAndMarkIn() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(requireActivity()).checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    markIn();
                } catch (ApiException exception) {
                    handleLocationSettingsException(exception);
                }
            }
        });
    }

    private void checkLocationSettingsAndMarkOut() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(requireActivity()).checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    markOut();
                } catch (ApiException exception) {
                    handleLocationSettingsException(exception);
                }
            }
        });
    }

    private void handleLocationSettingsException(ApiException exception) {
        switch (exception.getStatusCode()) {
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    ResolvableApiException resolvable = (ResolvableApiException) exception;
                    resolvable.startResolutionForResult(requireActivity(), REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Toast.makeText(getActivity(), "Location settings are inadequate, and cannot be fixed here. Fix in Settings.", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void markIn() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (prefs.getBoolean(MARKED_IN_KEY, false)) {
            Toast.makeText(getActivity(), "You have already marked in", Toast.LENGTH_SHORT).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour < 8 || hour >= 20) {
            Toast.makeText(getActivity(), "You can only mark in between 8 AM and 8 PM", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            // Retrieve saved location
                            double savedLatitude = Double.parseDouble(userPrefs.getString(MARK_IN_LAT_KEY, "0"));
                            double savedLongitude = Double.parseDouble(userPrefs.getString(MARK_OUT_LONG_KEY, "0"));

                            // Calculate distance
                            float[] results = new float[1];
                            Location.distanceBetween(latitude, longitude, savedLatitude, savedLongitude, results);
                            float distanceInMeters = results[0];

                            // Check if within 50 meters
                            if (distanceInMeters <= 50) {
                                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(calendar.getTime());
                                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());

                                String username = userPrefs.getString("username", ""); // Retrieve username from SharedPreferences

                                AttendanceModel attendance = new AttendanceModel();
                                attendance.setUsername(username);
                                attendance.setDate(currentDate);
                                attendance.setLogintime(currentTime);

                                ApiServiceattendance apiService = ApiClientattendance.getClient().create(ApiServiceattendance.class);
                                Call<AttendanceModel> call = apiService.markAttendance(attendance);
                                call.enqueue(new Callback<AttendanceModel>() {
                                    @Override
                                    public void onResponse(Call<AttendanceModel> call, Response<AttendanceModel> response) {
                                        if (response.isSuccessful()) {
                                            SharedPreferences.Editor editor = prefs.edit();
                                            editor.putBoolean(MARKED_IN_KEY, true);
                                            editor.putString(MARKED_IN_TIME_KEY, currentTime);
                                            editor.putString(MARK_IN_DATE_KEY, currentDate);
                                            editor.apply();

                                            Toast.makeText(getActivity(), "Marked in at: " + latitude + ", " + longitude + " at " + currentTime, Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getActivity(), "Failed to mark in. Please try again.", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AttendanceModel> call, Throwable t) {
                                        Toast.makeText(getActivity(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                Toast.makeText(getActivity(), "You are not at the designated location to mark in. Distance: " + distanceInMeters + " meters", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void markOut() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (!prefs.getBoolean(MARKED_IN_KEY, false)) {
            Toast.makeText(getActivity(), "You must mark in first", Toast.LENGTH_SHORT).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour < 8 || hour >= 20) {
            Toast.makeText(getActivity(), "You can only mark out between 8 AM and 8 PM", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST);
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(requireActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(calendar.getTime());
                            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());

                            String username = userPrefs.getString("username", ""); // Retrieve username from SharedPreferences

                            AttendanceModel attendance = new AttendanceModel();
                            attendance.setUsername(username);
                            attendance.setDate(currentDate);
                            attendance.setLogouttime(currentTime);

                            ApiServiceattendance apiService = ApiClientattendance.getClient().create(ApiServiceattendance.class);
                            Call<AttendanceModel> call = apiService.markAttendance(attendance);
                            call.enqueue(new Callback<AttendanceModel>() {
                                @Override
                                public void onResponse(Call<AttendanceModel> call, Response<AttendanceModel> response) {
                                    if (response.isSuccessful()) {
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putBoolean(MARKED_IN_KEY, false);
                                        editor.putString(LAST_MARK_OUT_LAT_KEY, String.valueOf(latitude));
                                        editor.putString(LAST_MARK_OUT_LON_KEY, String.valueOf(longitude));
                                        editor.putString(MARKED_OUT_TIME_KEY, currentTime);
                                        editor.putString(LAST_MARK_OUT_DATE_KEY, currentDate);
                                        editor.apply();

                                        Toast.makeText(getActivity(), "Marked out at: " + latitude + ", " + longitude + " at " + currentTime, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getActivity(), "Failed to mark out. Please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<AttendanceModel> call, Throwable t) {
                                    Toast.makeText(getActivity(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
    }
}
