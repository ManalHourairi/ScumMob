package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.network.ApiManager;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize ApiService using ApiManager
        apiService = ApiManager.getApiService();

        // Retrieve views
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Set click listener for login button
        buttonLogin.setOnClickListener(v -> {
            // Retrieve user input
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            UserLogin user=new UserLogin();
            user.setEmail(email);
            user.setPassword(password);
            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Email and password are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Make API call for authentication
            apiService.login(user).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful()) {
                        // Authentication successful, navigate to dashboard
                        navigateToDashboard();
                    } else {
                        // Authentication failed, display error message
                        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Failed to authenticate", Toast.LENGTH_SHORT).show();
                }
            });



        });
    }

    private void navigateToDashboard() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish(); // Optionally, finish the LoginActivity to prevent going back
    }
}
