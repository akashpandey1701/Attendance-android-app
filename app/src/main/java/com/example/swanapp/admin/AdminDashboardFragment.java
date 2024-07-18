package com.example.swanapp.admin;

import android.os.Bundle;
import android.util.Log;
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
import com.example.swanapp.network.ApiService;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminDashboardFragment extends Fragment {
    private RecyclerView recyclerViewEmployee;
    private RecyclerView recyclerViewAttendance;
    private RecyclerView recyclerViewVisit;

    private EmployeeAdapter employeeAdapter;
    private List<EmployeeRecord> employeeList;

    private Retrofit retrofit;
    private ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admindashboardfragment, container, false);

        recyclerViewEmployee = view.findViewById(R.id.recyclerViewEmployee);
        recyclerViewAttendance = view.findViewById(R.id.recyclerViewAttendance);
        recyclerViewVisit = view.findViewById(R.id.recyclerViewVisit);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.44.254:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        employeeList = new ArrayList<>();

        employeeAdapter = new EmployeeAdapter(getContext(), employeeList);
        recyclerViewEmployee.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewEmployee.setAdapter(employeeAdapter);

        fetchEmployeeData();

        return view;
    }

    private void fetchEmployeeData() {
        Call<List<EmployeeRecord>> call = apiService.getEmployeeRecords();
        call.enqueue(new Callback<List<EmployeeRecord>>() {
            @Override
            public void onResponse(Call<List<EmployeeRecord>> call, Response<List<EmployeeRecord>> response) {
                if (response.isSuccessful()) {
                    employeeList.clear();
                    if (response.body() != null) {
                        employeeList.addAll(response.body());
                    }
                    employeeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<EmployeeRecord>> call, Throwable t) {
                if (!isAdded()) {
                    Log.e("AdminDashboardFragment", "Fragment is not attached.");
                    return; // Fragment is not attached, cannot show Toast
                }

                if (getContext() != null) {
                    Toast.makeText(getContext(), "Request failed. Please try again.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("AdminDashboardFragment", "Context is null, cannot show Toast.");
                }
            }
        });
    }
}
