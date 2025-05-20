package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OnboardingThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is to set the layout file for the third onboarding screen.
        setContentView(R.layout.activity_onboarding_three)

        // This is to set the click listener for the "Get Started" button.
        // When clicked, it navigates to the MainActivity (login screen).
        findViewById<Button>(R.id.btn_get_started).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            // This finishes this onboarding screen to prevent the user to go back.
            finish()
        }
    }
}
