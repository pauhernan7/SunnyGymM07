package com.example.sunnygym

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GuidedActivitiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guided_activities)

        val activities: MutableList<Activity> = ArrayList()
        activities.add(Activity("Zumba", R.drawable.zumba_image, true))
        activities.add(Activity("Body Combat", R.drawable.body_combat_image, true))
        activities.add(Activity("Body Pump", R.drawable.body_pump_image, true))
        activities.add(Activity("Cycling", R.drawable.cycling, false))

        // Configurar RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewActivities)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ActivitiesAdapter(this, activities)
    }
}
