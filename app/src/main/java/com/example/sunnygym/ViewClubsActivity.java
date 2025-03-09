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

public class ViewClubsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clubs);

        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity("Meridiana", R.drawable.meridiana));
        activities.add(new Activity("Can Dragó", R.drawable.candrago));
        activities.add(new Activity("Diagonal", R.drawable.diagonal));

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewClubs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ActivitiesAdapter(this, activities));

    }

    /**
     * Método para mostrar el menú emergente
     *
     * @param anchorView Vista desde la cual se despliega el menú
     */
    private void showPopupMenu(View anchorView) {
        PopupMenu popupMenu = new PopupMenu(ViewClubsActivity.this, anchorView);
        popupMenu.getMenuInflater().inflate(R.menu.profile_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    startActivity(new Intent(ViewClubsActivity.this, UserProfileActivity.class));
                    return true;
                } else if (itemId == R.id.my_profile) {
                    startActivity(new Intent(ViewClubsActivity.this, MyProfileActivity.class));
                    return true;
                } else if (itemId == R.id.guided_activities) {
                    return true;
                } else if (itemId == R.id.view_clubs) {
                    return true;
                } else if (itemId == R.id.support) {
                    startActivity(new Intent(ViewClubsActivity.this, SupportActivity.class));
                    return true;
                } else if (itemId == R.id.logout) {
                    startActivity(new Intent(ViewClubsActivity.this, MainActivity.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();


    }
    // Crear lista de actividades

}
