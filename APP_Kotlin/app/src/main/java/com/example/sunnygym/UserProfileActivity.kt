package com.example.sunnygym

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity


class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Botón para generar el código QR
        val generateQRButton = findViewById<Button>(R.id.generateQRButton)
        generateQRButton.setOnClickListener {
            val intent = Intent(this@UserProfileActivity, QRCodeActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Método para mostrar el menú emergente
     *
     * @param anchorView Vista desde la cual se despliega el menú
     */
    private fun showPopupMenu(anchorView: View) {
        val popupMenu = PopupMenu(this@UserProfileActivity, anchorView)
        popupMenu.menuInflater.inflate(R.menu.profile_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            val itemId = item.itemId
            if (itemId == R.id.home) {
                // Ya estás en la actividad principal
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.my_profile) {
                startActivity(Intent(this@UserProfileActivity, MyProfileActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.guided_activities) {
                startActivity(
                    Intent(
                        this@UserProfileActivity,
                        GuidedActivitiesActivity::class.java
                    )
                )
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.view_clubs) {
                startActivity(Intent(this@UserProfileActivity, ViewClubsActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.support) {
                startActivity(Intent(this@UserProfileActivity, SupportActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.lista_activities) {
                startActivity(Intent(this@UserProfileActivity, SupportActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.logout) {
                startActivity(Intent(this@UserProfileActivity, MainActivity::class.java))
                finish()
                return@OnMenuItemClickListener true
            }
            false
        })

        popupMenu.show()
    }
}