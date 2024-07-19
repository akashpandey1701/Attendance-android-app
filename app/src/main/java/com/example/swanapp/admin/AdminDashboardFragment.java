package com.example.swanapp.admin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swanapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminDashboardFragment extends Fragment {

    private RecyclerView recyclerViewEmployee, recyclerViewAttendance, recyclerViewVisit;
    private EmployeeAdapter employeeAdapter;
    private AttendanceAdapter attendanceAdapter;
    private VisitAdapter visitAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admindashboardfragment, container, false);

        recyclerViewEmployee = view.findViewById(R.id.recyclerViewEmployee);
        recyclerViewEmployee.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewAttendance = view.findViewById(R.id.recyclerViewAttendance);
        recyclerViewAttendance.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerViewVisit = view.findViewById(R.id.recyclerViewVisit);
        recyclerViewVisit.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetch JWT token from SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String token = sharedPreferences.getString("JWT_TOKEN", null);

        if (token != null) {
            RetrofitClient retrofitClient = RetrofitClient.getInstance(token);
            ApiService apiService = retrofitClient.getApiService();

            fetchEmployees(apiService);
            fetchAttendance(apiService);
            fetchVisits(apiService);
        } else {
            Toast.makeText(getContext(), "Token is missing. Please log in again.", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void fetchEmployees(ApiService apiService) {
        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    employeeAdapter = new EmployeeAdapter(response.body());
                    recyclerViewEmployee.setAdapter(employeeAdapter);
                } else {
                    Toast.makeText(getContext(), "Failed to fetch employees", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchAttendance(ApiService apiService) {
        apiService.getAttendance().enqueue(new Callback<List<Attendance>>() {
            @Override
            public void onResponse(Call<List<Attendance>> call, Response<List<Attendance>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    attendanceAdapter = new AttendanceAdapter(response.body());
                    recyclerViewAttendance.setAdapter(attendanceAdapter);
                } else {
                    Toast.makeText(getContext(), "Failed to fetch attendance", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Attendance>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchVisits(ApiService apiService) {
        apiService.getVisits().enqueue(new Callback<List<Visit>>() {
            @Override
            public void onResponse(Call<List<Visit>> call, Response<List<Visit>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    visitAdapter = new VisitAdapter(response.body());
                    recyclerViewVisit.setAdapter(visitAdapter);
                } else {
                    Toast.makeText(getContext(), "Failed to fetch visits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Visit>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
