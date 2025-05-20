package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OnboardingTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is to set the layout file for the second onboarding screen.
        setContentView(R.layout.activity_onboarding_two)

        // This is to set a click listener for the "Skip" button
        // When clicked, it immediately navigates to the MainActivity (login screen).
        findViewById<Button>(R.id.btn_skip).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // This is to set a click listener for the "Next" button.
        // When clicked, it navigates to the next onboarding screen (OnboardingThreeActivity).
        findViewById<Button>(R.id.btn_next).setOnClickListener {
            startActivity(Intent(this, OnboardingThreeActivity::class.java))
            // This finishes this onboarding screen to prevent the user to go back.
            finish()
        }
    }
}
