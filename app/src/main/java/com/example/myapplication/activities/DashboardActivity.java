package com.example.myapplication.activities;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Get the welcome text view
        TextView welcomeText = findViewById(R.id.welcome_text);

        // Set a welcome message
        welcomeText.setText("Welcome to the Dashboard");
    }
}
