package com.example.threadlab101 // Corrected: Package name now matches your file's location

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Corrected: The import for View Binding now uses the correct package
import com.example.threadlab101.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for the Sign Up button
        binding.signUpButton.setOnClickListener {
            // TODO: Add actual sign-up validation and user creation logic

            // Navigate to HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            // Clear the task stack so user can't go back to login/signup
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Set click listener for the "Login" text
        binding.loginTextView.setOnClickListener {
            // Finish this activity to go back to the previous screen (which should be LoginActivity or MainActivity)
            finish()
        }
    }
}
