package com.example.goalphatask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 2000; // Splash screen duration in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delayed handler to navigate to the main activity after the splash duration
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);

                // Close the splash activity
                finish();
            }
        }, SPLASH_DURATION);
    }
}

