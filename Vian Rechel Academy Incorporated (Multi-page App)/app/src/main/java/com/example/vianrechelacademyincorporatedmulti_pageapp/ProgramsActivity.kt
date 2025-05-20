package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Color


class ProgramsActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programs)


        // Setup Toolbar + Navigation Drawer
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        // This is to set up the toolbar and navigation menu.
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.navigation_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // This sets the hamburger icon color to white.
        toggle.drawerArrowDrawable.color = Color.WHITE

        // This handles the navigation item clicks for navigation.
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.nav_about -> startActivity(Intent(this, AboutActivity::class.java))
                R.id.nav_programs -> startActivity(Intent(this, ProgramsActivity::class.java))
                R.id.nav_announcements -> startActivity(Intent(this, AnnouncementsActivity::class.java))
                R.id.nav_contact -> startActivity(Intent(this, ContactActivity::class.java))
                R.id.nav_profile -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.nav_faculty -> startActivity(Intent(this, FacultyActivity::class.java))
                R.id.nav_faqs -> startActivity(Intent(this, FaqsActivity::class.java))
                R.id.nav_logout -> {
                    // This handles logout logic by clearing the user session and redirecting the user to the login screen.
                    val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
            // This closes the drawer after the user's selection.
            drawerLayout.closeDrawers()
            true
        }

        // This is to set up the RecyclerView with vertical layout manager.
        val recyclerView = findViewById<RecyclerView>(R.id.programsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // I defined the list of school programs with their title and description.
        val programs = listOf(
            Program(
                "Pre-School",
                "Our Pre-School program offers a nurturing environment where young learners develop foundational skills in literacy, numeracy, and social interaction through play-based and structured activities. Guided by experienced educators, children are encouraged to explore, discover, and build confidence, setting the stage for lifelong learning."
            ),
            Program(
                "Elementary",
                "The Elementary program provides a comprehensive curriculum that fosters critical thinking, creativity, and moral values. Students engage in subjects such as Mathematics, Science, English, Filipino, and Araling Panlipunan, complemented by co-curricular activities that enhance their holistic development."
            ),
            Program(
                "Junior High School",
                "Designed for Grades 7 to 10, our Junior High School curriculum emphasizes academic excellence and character formation. Students delve deeper into core subjects and explore specialized areas, preparing them for the challenges of Senior High School and beyond."
            ),
            Program(
                "Senior High School",
                "Our Senior High School program offers specialized tracks to cater to students' interests and career goals. Available strands include:\n\n" +
                        "-ABM (Accountancy, Business, and Management): Focuses on business-related disciplines, preparing students for careers in commerce and entrepreneurship.\n" +
                        "-HUMSS (Humanities and Social Sciences): Emphasizes the study of human behavior and societal structures, ideal for those pursuing careers in education, communication, and social work.\n" +
                        "-Tech-Voc (Technical-Vocational): Provides practical skills in areas like culinary arts and housekeeping, equipping students with competencies for immediate employment.\n\n" +
                        "Notably, Vian Rechel Academy offers free tuition for incoming Grade 7 and Senior High School students under certain conditions."
            ),
            Program(
                "Special Programs",
                "Beyond the standard curriculum, Vian Rechel Academy offers special programs aimed at holistic development. These include extracurricular activities, leadership training, and community outreach initiatives, fostering well-rounded individuals ready to contribute positively to society."
            )
        )

        // I attached the custom adapter to the RecyclerView.
        val adapter = ProgramAdapter(programs)
        recyclerView.adapter = adapter


    }
}