package com.example.threadlab101

import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class PlannerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planner)

        // 1. Setup Toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        // 2. Setup Calendar
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        val selectedDateText = findViewById<TextView>(R.id.selectedDateText)
        val planTitle = findViewById<TextView>(R.id.planTitle)
        val btnAddPlan = findViewById<FloatingActionButton>(R.id.btnAddPlan)

        // Set default text to today
        val dateFormat = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
        selectedDateText.text = "Plan for ${dateFormat.format(Date())}"

        // Listen for date changes
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Note: Month is 0-indexed (0 = Jan, 11 = Dec)
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)

            val selectedDate = dateFormat.format(calendar.time)
            selectedDateText.text = "Plan for $selectedDate"

            // Reset the "Plan" text for demo purposes (In a real app, you'd load from database)
            planTitle.text = "No Outfit Planned"
        }

        // 3. Add Button Logic (Demo)
        btnAddPlan.setOnClickListener {
            planTitle.text = "✨ Chill Streetwear Look"
            Toast.makeText(this, "Outfit added to schedule!", Toast.LENGTH_SHORT).show()
        }
    }
}
