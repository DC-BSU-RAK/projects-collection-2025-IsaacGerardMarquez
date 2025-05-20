package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// I created this class to display the detailed information of the selected announcement.
class AnnouncementDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcement_detail)

        // This retrieves the title and description passed from the adapter.
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        // This sets the retrieved data to the respective TextViews.
        findViewById<TextView>(R.id.detailTitle).text = title
        findViewById<TextView>(R.id.detailDescription).text = description

        // I set up the back button to close the activity.
        val backButton = findViewById<TextView>(R.id.backButton)
        backButton.setOnClickListener {
            finish() // This closes the activity and returns the user to the previous screen.
        }
    }
}
