package com.example.threadlab101

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class WardrobeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wardrobe)

        // 1. Back Button Logic
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish() // Closes this screen and returns to Home
        }

        // 2. Card Interactions
        val cardTops = findViewById<MaterialCardView>(R.id.cardTops)
        val cardBottoms = findViewById<MaterialCardView>(R.id.cardBottoms)
        val cardShoes = findViewById<MaterialCardView>(R.id.cardShoes)

        cardTops.setOnClickListener {
            Toast.makeText(this, "Tops Collection Selected", Toast.LENGTH_SHORT).show()
        }

        cardBottoms.setOnClickListener {
            Toast.makeText(this, "Bottoms Collection Selected", Toast.LENGTH_SHORT).show()
        }

        cardShoes.setOnClickListener {
            Toast.makeText(this, "Shoes Collection Selected", Toast.LENGTH_SHORT).show()
        }
    }
}
