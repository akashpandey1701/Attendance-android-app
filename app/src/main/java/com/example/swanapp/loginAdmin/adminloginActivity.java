package com.example.swanapp.loginAdmin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.swanapp.R;
import com.example.swanapp.admin.adminActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class adminloginActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);

        userIdEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editText2);
        loginButton = findViewById(R.id.button3);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUserId = userIdEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                JwtRequest request = new JwtRequest(enteredUserId, enteredPassword);
                adminLogin(request);
            }
        });
    }

    private void adminLogin(JwtRequest request) {
        apiInterface.adminLogin(request).enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if (response.isSuccessful()) {
                    String token = response.body().getToken();

                    // Save token in SharedPreferences
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(adminloginActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("JWT_TOKEN", token);
                    editor.apply();

                    Intent intent = new Intent(adminloginActivity.this, adminActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(adminloginActivity.this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                Toast.makeText(adminloginActivity.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
