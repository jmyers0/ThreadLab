package com.example.threadlab101

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch

// RENAMED: Class is now GeneratorActivity
class GeneratorActivity : AppCompatActivity() {

    private val geminiHelper = GeminiHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure you rename your layout file to activity_generator or keep using activity_mix_match if you prefer
        setContentView(R.layout.activity_generator)

        // 1. Setup Toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.title = "OUTFIT GENERATOR" // Update title
        toolbar.setNavigationOnClickListener { finish() }

        // 2. Find Views
        val aiTitleText = findViewById<TextView>(R.id.aiTitleText)
        // You might want to rename this ID in XML to btnGenerate later, but keeping ID for now
        val btnShuffle = findViewById<Button>(R.id.btnShuffle)

        // 3. Setup Click Listener
        btnShuffle.setOnClickListener {
            btnShuffle.text = "GENERATING..."
            Toast.makeText(this, "AI is styling your look...", Toast.LENGTH_SHORT).show()

            lifecycleScope.launch {
                // Call Gemini
                val suggestion = geminiHelper.getOutfitSuggestion("Streetwear", "Casual Day Out")

                if (suggestion != null) {
                    aiTitleText.text = suggestion
                } else {
                    aiTitleText.text = "Could not generate outfit."
                }
                btnShuffle.text = "GENERATE NEW FIT"
            }
        }
    }
}
