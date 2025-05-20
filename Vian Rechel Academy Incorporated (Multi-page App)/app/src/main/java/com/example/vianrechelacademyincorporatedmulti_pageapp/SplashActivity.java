package com.example.vianrechelacademyincorporatedmulti_pageapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // This delays the splash screen by 3 seconds before going to OnboardingOneActivity.
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, OnboardingOneActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // This is the time for the splash screen.
    }
}
