package com.example.threadlab101 // Corrected: Package name now matches the file's location

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Corrected: The import for View Binding now uses the correct package
import com.example.threadlab101.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // Declare the binding variable
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the Login button
        binding.loginButton.setOnClickListener {
            // TODO: Add actual login validation logic here

            // Navigate to HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            // Clear the back stack so the user cannot return to the login screen after logging in
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Set click listener for the "Sign Up" text
        binding.signUpTextView.setOnClickListener {
            // Navigate to SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
