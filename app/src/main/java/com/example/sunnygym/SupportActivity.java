package com.example.sunnygym;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        // Referencia al botón de enviar
        Button sendButton = findViewById(R.id.sendButton);



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un Toast al hacer clic en el botón
                Toast.makeText(SupportActivity.this, "Mensaje enviado correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Método para mostrar el menú emergente
     *
     * @param anchorView Vista desde la cual se despliega el menú
     */
    private void showPopupMenu(View anchorView) {
        PopupMenu popupMenu = new PopupMenu(SupportActivity.this, anchorView);
        popupMenu.getMenuInflater().inflate(R.menu.profile_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    startActivity(new Intent(SupportActivity.this, UserProfileActivity.class));
                    return true;
                } else if (itemId == R.id.my_profile) {
                    startActivity(new Intent(SupportActivity.this, MyProfileActivity.class));
                    return true;
                } else if (itemId == R.id.guided_activities) {
                    startActivity(new Intent(SupportActivity.this, GuidedActivitiesActivity.class));
                    return true;
                } else if (itemId == R.id.view_clubs) {
                    startActivity(new Intent(SupportActivity.this, ViewClubsActivity.class));
                    return true;
                } else if (itemId == R.id.support) {
                    return true;
                } else if (itemId == R.id.logout) {
                    startActivity(new Intent(SupportActivity.this, MainActivity.class));
                    finish();
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();

    }
}
