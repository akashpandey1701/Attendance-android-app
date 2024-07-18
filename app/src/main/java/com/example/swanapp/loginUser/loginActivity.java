package com.example.swanapp.loginUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swanapp.R;
import com.example.swanapp.loginAdmin.adminloginActivity;
import com.example.swanapp.user.secondActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button adminButton;
    private EditText userIdEditText;
    private EditText passwordEditText;

    private loginApiinterface loginApiinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginApiinterface = loginApiclient.getRetrofitInstance().create(loginApiinterface.class);

        userIdEditText = findViewById(R.id.editText);
        passwordEditText = findViewById(R.id.editText2);
        loginButton = findViewById(R.id.button);
        adminButton = findViewById(R.id.button2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUserId = userIdEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                JwtRequest request = new JwtRequest(enteredUserId, enteredPassword);
                userLogin(request);
            }
        });


        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(loginActivity.this, adminloginActivity.class));
            }
        });
    }

    private void userLogin(JwtRequest request) {
        loginApiinterface.userLogin(request).enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if (response.isSuccessful()) {
                    String token = response.body().getToken();
                    Intent intent = new Intent(loginActivity.this, secondActivity.class);
                    intent.putExtra("TOKEN", token);
                    startActivity(intent);
                } else {
                    Toast.makeText(loginActivity.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                Toast.makeText(loginActivity.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
