package com.example.vianrechelacademyincorporatedmulti_pageapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// I created this class to allow the users to change their password.
class ChangePasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        // This is the back button to close this activity and return to previous screen.
        val backButton = findViewById<TextView>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        // These are the input fields for the current, new, and confirm password.
        val currentPasswordEditText = findViewById<EditText>(R.id.editTextCurrentPassword)
        val newPasswordEditText = findViewById<EditText>(R.id.editTextNewPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextConfirmPassword)
        val buttonChangePassword = findViewById<Button>(R.id.buttonConfirmChange)

        // These are the checkboxes to toggle visibility of password inputs.
        val showCurrentPasswordCheckBox = findViewById<CheckBox>(R.id.checkBoxShowCurrentPassword)
        val showNewPasswordCheckBox = findViewById<CheckBox>(R.id.checkBoxShowNewPassword)
        val showConfirmPasswordCheckBox = findViewById<CheckBox>(R.id.checkBoxShowConfirmPassword)

        // This retrieves the saved password from SharedPreferences.
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedPassword = sharedPref.getString("password", "") ?: ""

        // This toggles the visibility listeners for each password field.
        showCurrentPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            togglePasswordVisibility(currentPasswordEditText, isChecked)
        }

        showNewPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            togglePasswordVisibility(newPasswordEditText, isChecked)
        }

        showConfirmPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            togglePasswordVisibility(confirmPasswordEditText, isChecked)
        }

        // This handles the password change button click.
        buttonChangePassword.setOnClickListener {
            val currentPassword = currentPasswordEditText.text.toString().trim()
            val newPassword = newPasswordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // This validates the current password input against the saved password.
            if (currentPassword != savedPassword.trim()) {
                Toast.makeText(this, "Current password is incorrect.", Toast.LENGTH_SHORT).show()
            }
            // This validates the new password and confirmation match.
            else if (newPassword != confirmPassword) {
                Toast.makeText(this, "New passwords do not match.", Toast.LENGTH_SHORT).show()
            }
            // This ensures that the minimum password length is 6 characters.
            else if (newPassword.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show()
            }
            // If all validations passed, the password is updated and navigates the user back to Profile.
            else {
                sharedPref.edit().putString("password", newPassword).apply()
                Toast.makeText(this, "Password changed successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
        }
    }

    // This is the function to toggle password visibility of an EditText.
    private fun togglePasswordVisibility(editText: EditText, showPassword: Boolean) {
        if (showPassword) {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        // This moves the cursor to the end of the input after changing inputType.
        editText.setSelection(editText.text.length)
    }
}
