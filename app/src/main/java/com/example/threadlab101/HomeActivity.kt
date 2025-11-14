package com.example.threadlab101 // Corrected: Package name now matches the file's location.

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Corrected: Import statement updated to the new package name.
import com.example.threadlab101.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // You can add click listeners for your feature buttons here
        // Example:
        // binding.wardrobeButton.setOnClickListener {
        //     startActivity(Intent(this, WardrobeActivity::class.java))
        // }
    }
}
