package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // This declares the UI elements as lateinit variables.
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var showPasswordCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is to set the layout file for this activity.
        setContentView(R.layout.activity_main)

        // This initializes the UI elements by linking them with their IDs in the layout XML.
        usernameEditText = findViewById(R.id.editTextUsername)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)
        showPasswordCheckBox = findViewById(R.id.checkBoxShowPassword)

        // This toggles the password visibility when the checkbox is clicked.
        showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // This shows the password.
                passwordEditText.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or
                            android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                // This hides the password.
                passwordEditText.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or
                            android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            // This moves the cursor to the end of the text after toggling inputType.
            passwordEditText.setSelection(passwordEditText.text.length)
        }

        // This handles the login button click event.
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // This validates the input fields.
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            } else {
                // This saves the username and password to SharedPreferences.
                val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                sharedPref.edit()
                    .putString("username", username)
                    .putString("password", password)
                    .apply()
                // Navigate to HomeActivity after successful login
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()// This finishes the current activity to prevent going back to login screen.
            }
        }
    }
}
