package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import android.graphics.Color

// This is the activity to display a list of announcements.
class AnnouncementsActivity : AppCompatActivity() {

    // I declared these three UI components.
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var announcementRecyclerView: RecyclerView
    private lateinit var adapter: AnnouncementAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_announcements)

        // I initialized the toolbar and navigation drawer.
        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        val navView = findViewById<NavigationView>(R.id.navigation_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // This sets hamburger icon color to white.
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
            // This closes the drawer after selection.
            drawerLayout.closeDrawers()
            true
        }

        // This handles system UI padding for edge-to-edge layout.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // I set up the RecyclerView to display the announcements.
        announcementRecyclerView = findViewById(R.id.announcementRecyclerView)
        announcementRecyclerView.layoutManager = LinearLayoutManager(this)

        // These are the announcements:
        val announcements = listOf(
            Announcement(
                "Schedule Update",
                "Due to weather conditions, classes for Grades 9-12 will begin at 9:00 AM tomorrow, Friday, May 18. Please plan your commute accordingly and check the portal for any subject-specific updates."
            ),
            Announcement(
                "Exam Reminder",
                "The Midterm Exams for Junior High School will commence on Monday, May 20. Timetables have been posted on the class group chats and the LMS. Don’t forget to bring your school ID."
            ),
            Announcement(
                "Holiday Notice",
                "In observance of Independence Day, the school will be closed on Wednesday, June 12. No classes or activities will be held on campus or online."
            ),
            Announcement(
                "Parent-Teacher Conference",
                "The quarterly Parent-Teacher Conference is scheduled for Saturday, May 25, from 8:00 AM to 12:00 PM at the Multipurpose Hall. Please confirm your attendance with the class adviser."
            ),
            Announcement(
                "Lost and Found",
                "A silver water bottle and a navy blue jacket were found near the gym on May 15. Please claim lost items at the Admin Office before Friday."
            ),
            Announcement(
                "Field Trip Consent Deadline",
                "Consent forms for the Grade 8 Science Field Trip must be submitted by Tuesday, May 21. Late submissions will not be accepted."
            ),
            Announcement(
                "Library Book Return",
                "All library books must be returned by Friday, May 31 to avoid late fees. Visit the library during break hours or after class for returns and renewals."
            ),
            Announcement(
                "Club Showcase Week",
                "The annual Club Showcase will take place from May 27–31 in the quadrangle during lunch breaks. Come and support your classmates and learn how to get involved next year!"
            ),
            Announcement(
                "Uniform Inspection",
                "A random uniform inspection will be conducted on Monday, May 20. Ensure that you are wearing the proper school attire and ID at all times."
            ),
            Announcement(
                "Health Declaration Reminder",
                "Please remember to fill out the daily health declaration form via the school app before arriving on campus. This helps us keep everyone safe and healthy."
            )
        )

        // I initialized and set the adapter with the announcements list.
        val adapter = AnnouncementAdapter(announcements)
        announcementRecyclerView.adapter = adapter
    }
}
