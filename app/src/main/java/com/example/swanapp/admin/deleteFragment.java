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
//import com.example.swanapp.network.RetrofitClientInstance;
//import com.example.swanapp.network.deleteUserInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deleteFragment extends Fragment {

    private EditText userId;
    private Button deleteButton;

    public deleteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        userId = view.findViewById(R.id.user_id);
        deleteButton = view.findViewById(R.id.delete);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });

        return view;
    }

    private void deleteUser() {
        String userIdStr = userId.getText().toString().trim();

        if (userIdStr.isEmpty()) {
            showToast("Please enter User ID");
            return;
        }

        deleteUserInterface service = deleteRetrofitInstance.getRetrofitInstance().create(deleteUserInterface.class);
        Call<Void> call = service.deleteUser(userIdStr);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("DeleteFragment", "User deleted successfully");
                    showToast("User deleted successfully");
                } else {
                    Log.e("DeleteFragment", "Failed to delete user: " + response.code());
                    showToast("Failed to delete user");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("DeleteFragment", "Error: " + t.getMessage(), t);
                showToast("Error: " + t.getMessage());
            }
        });
    }

    private void showToast(final String message) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
