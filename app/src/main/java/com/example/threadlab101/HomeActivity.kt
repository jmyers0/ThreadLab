package com.example.threadlab101

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import kotlin.math.abs

class HomeActivity : AppCompatActivity() {

    private var viewPager: ViewPager2? = null
    private val sliderHandler = Handler(Looper.getMainLooper())
    private val indicators = mutableListOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // --- SLIDESHOW SETUP ---
        viewPager = findViewById(R.id.slideshowViewPager)
        val indicatorLayout = findViewById<LinearLayout>(R.id.indicatorLayout)

        if (indicatorLayout != null) {
            for (i in 0 until indicatorLayout.childCount) {
                indicators.add(indicatorLayout.getChildAt(i))
            }
        }

        val sliderImages = listOf(
            R.drawable.essentials,
            R.drawable.essentials2,
            R.drawable.essentials3
        )

        val adapter = SlideshowAdapter(sliderImages)

        viewPager?.let { vp ->
            vp.adapter = adapter
            vp.setPageTransformer { page, position ->
                val minScale = 0.85f
                val minAlpha = 0.5f
                page.apply {
                    val width = width
                    val height = height
                    val scaleFactor = Math.max(minScale, 1 - abs(position))
                    val vertMargin = height * (1 - scaleFactor) / 2
                    val horzMargin = width * (1 - scaleFactor) / 2

                    if (position < 0) {
                        translationX = horzMargin - vertMargin / 2
                    } else {
                        translationX = -horzMargin + vertMargin / 2
                    }
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha = (minAlpha + (((scaleFactor - minScale) / (1 - minScale)) * (1 - minAlpha)))
                }
            }
            vp.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    updateIndicators(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, 6000)
                }
            })
        }

        // --- MAIN CARD BUTTONS ---

        // 1. Generator Button
        findViewById<MaterialCardView>(R.id.generatorCard)?.setOnClickListener { view ->
            animateClick(view) {
                startActivity(Intent(this, GeneratorActivity::class.java))
            }
        }

        // 2. Planner Button
        findViewById<MaterialCardView>(R.id.plannerCard)?.setOnClickListener { view ->
            animateClick(view) {
                startActivity(Intent(this, PlannerActivity::class.java))
            }
        }

        // 3. Mix & Match Button (FIXED: NOW OPENS MixMatchActivity)
        findViewById<MaterialCardView>(R.id.mixMatchCard)?.setOnClickListener { view ->
            animateClick(view) {
                startActivity(Intent(this, GeneratorActivity::class.java))
            }
        }

        // 4. My Wardrobe Button
        findViewById<MaterialCardView>(R.id.wardrobeCard)?.setOnClickListener { view ->
            animateClick(view) {
                startActivity(Intent(this, WardrobeActivity::class.java))
            }
        }

        // --- BOTTOM NAVIGATION SETUP ---
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_wardrobe -> {
                    startActivity(Intent(this, WardrobeActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Profile coming soon!", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun updateIndicators(position: Int) {
        for (i in indicators.indices) {
            val safePosition = position % indicators.size
            if (i == safePosition) {
                indicators[i].background.setTint(Color.parseColor("#00E5FF"))
                indicators[i].alpha = 1.0f
            } else {
                indicators[i].background.setTint(Color.parseColor("#FFFFFF"))
                indicators[i].alpha = 0.5f
            }
        }
    }

    private fun animateClick(view: View, onAnimationEnd: () -> Unit) {
        view.animate()
            .scaleX(0.95f)
            .scaleY(0.95f)
            .setDuration(100)
            .withEndAction {
                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .withEndAction {
                        onAnimationEnd()
                    }
                    .start()
            }
            .start()
    }

    private val sliderRunnable = object : Runnable {
        override fun run() {
            viewPager?.let { vp ->
                val currentItem = vp.currentItem
                val totalItems = vp.adapter?.itemCount ?: 0
                if (totalItems > 0) {
                    if (currentItem < totalItems - 1) {
                        vp.currentItem = currentItem + 1
                    } else {
                        vp.currentItem = 0
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 6000)
    }
}
