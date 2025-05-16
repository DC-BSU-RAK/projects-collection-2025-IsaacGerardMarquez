package com.example.genlingonancalculator

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

// This is the activity that displays instructions to the user.
class InstructionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is to set the layout for the instruction screen.
        setContentView(R.layout.activity_instruction)

        // This is to find the back button in the layout.
        val backButton = findViewById<ImageButton>(R.id.backButton)
        // I set a click listener to handle back button press.
        backButton.setOnClickListener {
            finish() // This closes the activity and return to the MainActivity.
        }
    }
}
