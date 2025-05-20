package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import android.graphics.Color


class ContactActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

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

        // I applied window insets to ensure alignment.
        val mainLayout = findViewById<LinearLayout>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                v.paddingLeft,
                systemBars.top,
                v.paddingRight,
                systemBars.bottom
            )
            insets
        }

        // These are the clickable contact icons that redirect the user to the school's respective links.
        findViewById<ImageView>(R.id.facebookIcon).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/VianRechelAcademy/"))
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.messengerIcon).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/VianRechelAcademy"))
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.emailIcon).setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:rechelvian1993VRA@gmail.com")
            }
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.phoneIcon).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:+63270061341")
            }
            startActivity(intent)
        }

        // This setups the WebView to embed a Google Map for Vian Rechel Academy's location.
        val mapWebView = findViewById<WebView>(R.id.mapWebView)
        mapWebView.settings.javaScriptEnabled = true
        // I used a WebViewClient to load URLs inside the app instead of external browser.
        mapWebView.webViewClient = WebViewClient()
        mapWebView.loadUrl("https://www.google.com/maps/place/Vian+Rechel+Academy,+Philippines")
    }
}
