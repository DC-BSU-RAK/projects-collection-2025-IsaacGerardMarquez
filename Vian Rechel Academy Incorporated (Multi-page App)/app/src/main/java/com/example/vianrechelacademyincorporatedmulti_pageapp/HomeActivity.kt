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


// This is the main screen for the app with quotes, announcements, tasks, date and time, and the navigation drawer.
class HomeActivity : AppCompatActivity() {

    // This is a DrawerLayout instance for the navigation drawer.
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout file for this activity
        setContentView(R.layout.activity_home)

        // This a list that stores motivational quotes that would be displayed randomly.
        val quotes = listOf(
            "“The beautiful thing about learning is nobody can take it away from you.” — B.B. King",
            "“Live as if you were to die tomorrow. Learn as if you were to live forever.” — Mahatma Gandhi",
            "“Education is the passport to the future, for tomorrow belongs to those who prepare for it today.” — Malcolm X",
            "“An investment in knowledge pays the best interest.” — Benjamin Franklin",
            "“Education is not the filling of a pail, but the lighting of a fire.” — William Butler Yeats",
            "“The roots of education are bitter, but the fruit is sweet.” — Aristotle",
            "“Develop a passion for learning. If you do, you will never cease to grow.” — Anthony J. D'Angelo",
            "“The more that you read, the more things you will know. The more that you learn, the more places you’ll go.” — Dr. Seuss",
            "“Education is the most powerful weapon which you can use to change the world.” — Nelson Mandela",
            "“Intelligence plus character—that is the goal of true education.” — Martin Luther King Jr.",
            "“Education is the key to unlocking the world, a passport to freedom.” — Oprah Winfrey",
            "“Learning never exhausts the mind.” — Leonardo da Vinci",
            "“Education is the foundation upon which we build our future.” — Christine Gregoire",
            "“Knowledge is power. Information is liberating. Education is the premise of progress.” — Kofi Annan",
            "“Tell me and I forget. Teach me and I remember. Involve me and I learn.” — Benjamin Franklin",
            "“Education’s purpose is to replace an empty mind with an open one.” — Malcolm Forbes"
        )

        // This is to find the UI elements for displaying quotes and for the button that triggers a new quote.
        val textViewQuote = findViewById<TextView>(R.id.textViewQuote)
        val buttonNewQuote = findViewById<Button>(R.id.buttonNewQuote)

        // I set a click listener on the button to show a random quote and a toast message.
        buttonNewQuote.setOnClickListener {
            val randomQuote = quotes.random()
            textViewQuote.text = randomQuote
            Toast.makeText(this, "Here's your quote!", Toast.LENGTH_SHORT).show()
        }

        // This shows the current date and time.
        val textViewDateTime = findViewById<TextView>(R.id.textViewDateTime)
        val currentDateTime = SimpleDateFormat("EEEE, MMMM d, yyyy - hh:mm a", Locale.getDefault()).format(Date())
        textViewDateTime.text = currentDateTime

        // This shows a toast for a new announcement.
        Toast.makeText(this, "New announcement available!", Toast.LENGTH_LONG).show()

        // This is to set up the RecyclerView to display a horizontal scrolling list of tasks or announcements.
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        val taskList = listOf(
            Task("Math Assignment", "Complete exercises 5 to 10 on page 87. Due: May 21, 2025."),
            Task("Science Project", "Submit your video presentation and research document on renewable energy via Google Classroom by May 22, 2025."),
            Task("Final Exam Review Materials", "Review guides for all subjects will be available in the Student Resources section starting May 20, 2025."),
            Task("School-Wide Announcement", "Vian Rechel Academy will hold its Recognition Day on June 7, 2025. Check the Announcements section for full details."),
            Task("PTA General Assembly", "Parents and guardians are invited to the PTA meeting on May 25, 2025, at 3:00 PM in the school auditorium.")
        )

        // This configures the RecyclerView for horizontal layout and set its adapter with the task list.
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = TaskAdapter(taskList)

        // This gets the username from shared preferences and displays a personalized welcome message that uses the user's input.
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")
        findViewById<TextView>(R.id.textViewWelcome).text = "Welcome, $username!"

        // This sets up the toolbar.
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        // This is to set up the toolbar and navigation menu.
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.navigation_view)

        // This is to enable the hamburger menu toggle.
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

    }
}