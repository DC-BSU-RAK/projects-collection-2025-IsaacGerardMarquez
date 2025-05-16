package com.example.genlingonancalculator

import android.content.Intent
import android.os.*
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

// This for the Splash screen activity shown upon launching the app/
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // This finds the ImageView from the layout (logo).
        val logo = findViewById<ImageView>(R.id.logo)

        // This is to load the fade-in animation from res/anim/fade_in.xml.
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        // This applies the fade-in animation to the logo.
        logo.startAnimation(fadeIn)

        // This is to delay the transition to MainActivity by 3 seconds (3000 ms).
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            // This finishes SplashActivity so it's removed from the back stack.
            finish()
        }, 3000) // 3 seconds
    }
}
