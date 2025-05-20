package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// This activity displays the full details of a selected program by retrieving the title and description of the program through an Intent.
class ProgramDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is to set the layout for this activity.
        setContentView(R.layout.activity_program_detail)

        // This retrieves the program title and description passed from the previous activity.
        val title = intent.getStringExtra("programTitle")
        val description = intent.getStringExtra("programDescription")

        // This binds the TextViews to display the title and description.
        val titleTextView: TextView = findViewById(R.id.detailProgramTitle)
        val descriptionTextView: TextView = findViewById(R.id.detailProgramDescription)
        val backButton: TextView = findViewById(R.id.backButton)

        // This sets the retrieved title and description to the TextViews.
        titleTextView.text = title
        descriptionTextView.text = description

        // This is to set up a click listener for the back button to finish the activity and return to the previous screen.
        backButton.setOnClickListener {
            finish()
        }
    }
}
