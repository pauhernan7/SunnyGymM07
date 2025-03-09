package com.example.sunnygym;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Botón para generar el código QR
        Button generateQRButton = findViewById(R.id.generateQRButton);
        generateQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, QRCodeActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Método para mostrar el menú emergente
     *
     * @param anchorView Vista desde la cual se despliega el menú
     */
    private void showPopupMenu(View anchorView) {
        PopupMenu popupMenu = new PopupMenu(UserProfileActivity.this, anchorView);
        popupMenu.getMenuInflater().inflate(R.menu.profile_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    // Ya estás en la actividad principal
                    return true;
                } else if (itemId == R.id.my_profile) {
                    startActivity(new Intent(UserProfileActivity.this, MyProfileActivity.class));
                    return true;
                } else if (itemId == R.id.guided_activities) {
                    startActivity(new Intent(UserProfileActivity.this, GuidedActivitiesActivity.class));
                    return true;
                } else if (itemId == R.id.view_clubs) {
                    startActivity(new Intent(UserProfileActivity.this, ViewClubsActivity.class));
                    return true;
                } else if (itemId == R.id.support) {
                    startActivity(new Intent(UserProfileActivity.this, SupportActivity.class));
                    return true;
                } else if (itemId == R.id.lista_activities) {
                        startActivity(new Intent(UserProfileActivity.this, SupportActivity.class));
                        return true;
                } else if (itemId == R.id.logout) {
                    startActivity(new Intent(UserProfileActivity.this, MainActivity.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}