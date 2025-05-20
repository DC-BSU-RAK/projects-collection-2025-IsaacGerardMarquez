package com.example.vianrechelacademyincorporatedmulti_pageapp

import CarouselAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import android.graphics.Color


class AboutActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    // These are the carousel image lists for the PVMG of Vian Rechel Academy Inc.
    private val missionImages = listOf(
        R.drawable.mission,
        R.drawable.vision,
        R.drawable.goals,
        R.drawable.philosophy
    )

    // These are the carousel image lists for the three student testimonials of Vian Rechel Academy Inc.
    private val testimonialImages1 = listOf(
        R.drawable.studenttestimonial1,
        R.drawable.studenttestimonial2,
        R.drawable.studenttestimonial3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // This is to set up the toolbar and navigation menu.
        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

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

        // This handles system UI padding for edge-to-edge layout.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // This setups and initializes the horizontal carousels.
        setupCarousel(R.id.missionRecyclerView, missionImages)
        setupCarousel(R.id.testimonialRecyclerView1, testimonialImages1)
    }

    // This configures the horizontal carousel with snapping effect.
    private fun setupCarousel(recyclerViewId: Int, images: List<Int>) {
        val recyclerView = findViewById<RecyclerView>(recyclerViewId)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = CarouselAdapter(images)

        // This puts a snap effect to center the carousel.
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        // This is to add both start and end padding to ensure that the items are centered with space on edges.
        recyclerView.setPadding(40, 0, 40, 0)
        recyclerView.clipToPadding = false
    }
}
