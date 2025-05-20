package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.vianrechelacademyincorporatedmulti_pageapp.R
import android.graphics.Color

// I created this class to display the Frequently Asked Questions (FAQs) organized by categories.
class FaqsActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    // Here are the list of FAQs under the Admissions category with the questions paired with their answers.
    private val admissionsFaqs = listOf(
        "What grade levels does Vian Rechel Academy Inc. offer?" to "Vian Rechel Academy Inc. provides education from Pre-School through Secondary levels, encompassing Elementary and High School programs.",
        "Is the school accredited by the Department of Education (DepEd)?" to "Yes, Vian Rechel Academy Inc. is an accredited private school recognized by the Department of Education in the Philippines.",
        "What are the requirements for admission?" to "Prospective students typically need to submit a completed application form, previous academic records, a birth certificate, and undergo an entrance assessment.",
        "When does the enrollment period start?" to "Enrollment periods are usually announced prior to the start of the academic year. It's advisable to contact the school directly for specific dates.",
        "Is there an entrance examination for new students?" to "Yes, new applicants may be required to take an entrance examination to assess their academic readiness."
    )

    // Here are the list of FAQs under the Academics category with the questions paired with their answers.
    private val academicsFaqs = listOf(
        "What curriculum does the school follow?" to "Vian Rechel Academy Inc. adheres to the K-12 curriculum mandated by the Department of Education, ensuring a comprehensive educational program.",
        "What academic strands are offered in Senior High School?" to "The school offers various strands in Senior High School, including ABM (Accountancy, Business, and Management), HUMSS (Humanities and Social Sciences), and Technical-Vocational tracks such as Culinary and Housekeeping.",
        "What subjects are covered in the Junior High School curriculum?" to "The Junior High School curriculum includes core subjects like Science, Mathematics, English, Filipino, and Araling Panlipunan (Social Studies), along with MAPEH (Music, Arts, Physical Education, and Health), Edukasyon sa Pagpapakatao (Values Education), and Technology and Livelihood Education (TLE).",
        "How does the school support students who are struggling academically?" to "Vian Rechel Academy Inc. offers academic support through remedial classes, peer tutoring, and consultations with teachers to help students improve in challenging subjects.",
        "Are digital tools or e-learning platforms used in teaching?" to "Yes, the school integrates technology into learning by using digital tools and e-learning platforms to enhance classroom instruction and student engagement."
    )

    // Here are the list of FAQs under the General Information category with the questions paired with their answers.
    private val generalFaqs = listOf(
        "Where is Vian Rechel Academy Inc. located?" to "The school is situated on Magsaysay Avenue, Barangay Magsaysay, San Pedro City, Laguna, Philippines.",
        "What are the school's contact details?" to "You can reach the school at (02) 420-7521 or via email at rechelvian1993vra@gmail.com",
        "What are the school hours?" to "School hours typically run from 9:00 AM to 5:00 PM. For specific schedules, please contact the school directly.",
        "Does the school have a uniform policy?" to "Yes, students are required to wear the prescribed school uniform during school hours and official functions.",
        "Is transportation provided for students?" to "For information regarding student transportation services, please inquire with the school's administrative office."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs)

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
                    // This handles logout logic by clearing the user session and redirecting the user to the login screen
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

        // This is the container LinearLayout where all FAQ views will be added.
        val faqContainer = findViewById<LinearLayout>(R.id.faq_container)

        // This is the helper used to add the category title.
        fun addCategoryTitle(title: String) {
            val tv = TextView(this)
            tv.text = title
            tv.setTextColor(resources.getColor(R.color.green, null))
            tv.textSize = 20f
            tv.setPadding(0, 24, 0, 8)
            tv.setTypeface(null, android.graphics.Typeface.BOLD)
            faqContainer.addView(tv)
        }

        // This is another helper to add each FAQ item with toggle behavior.
        fun addFaqItem(question: String, answer: String) {
            val inflater = LayoutInflater.from(this)
            val faqView = inflater.inflate(R.layout.faq_item, faqContainer, false)

            // This gets the references to views in the FAQ item layout.
            val questionBox = faqView.findViewById<LinearLayout>(R.id.questionBox)
            val questionText = faqView.findViewById<TextView>(R.id.textViewQuestion)
            val answerText = faqView.findViewById<TextView>(R.id.textViewAnswer)
            val toggleIcon = faqView.findViewById<ImageView>(R.id.imageViewToggle)

            // This sets the question and answer text.
            questionText.text = question
            answerText.text = answer

            // This initially hides the answers.
            questionBox.setOnClickListener {
                if (answerText.visibility == View.GONE) {
                    answerText.visibility = View.VISIBLE
                    toggleIcon.setImageResource(R.drawable.ic_minus)
                } else {
                    answerText.visibility = View.GONE
                    toggleIcon.setImageResource(R.drawable.ic_plus)
                }
            }

            // This adds the the FAQ item view to the container.
            faqContainer.addView(faqView)
        }

        // This adds all the FAQ categories and their items in the layout.
        addCategoryTitle("Admissions")
        admissionsFaqs.forEach { (q, a) -> addFaqItem(q, a) }

        addCategoryTitle("Academics")
        academicsFaqs.forEach { (q, a) -> addFaqItem(q, a) }

        addCategoryTitle("General Information")
        generalFaqs.forEach { (q, a) -> addFaqItem(q, a) }
    }
}