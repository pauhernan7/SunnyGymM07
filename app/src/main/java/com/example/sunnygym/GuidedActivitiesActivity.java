package com.example.sunnygym;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GuidedActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_activities);

        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("Zumba", R.drawable.zumba_image));
        activities.add(new Activity("Body Combat", R.drawable.body_combat_image));
        activities.add(new Activity("Body Pump", R.drawable.body_pump_image));

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewActivities);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ActivitiesAdapter(this, activities));
    }
}
