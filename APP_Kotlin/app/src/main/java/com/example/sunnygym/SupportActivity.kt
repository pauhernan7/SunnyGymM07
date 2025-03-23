package com.example.sunnygym

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SupportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)

        // Referencia al botón de enviar
        val sendButton = findViewById<Button>(R.id.sendButton)



        sendButton.setOnClickListener { // Mostrar un Toast al hacer clic en el botón
            Toast.makeText(
                this@SupportActivity,
                "Mensaje enviado correctamente",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Método para mostrar el menú emergente
     *
     * @param anchorView Vista desde la cual se despliega el menú
     */
    private fun showPopupMenu(anchorView: View) {
        val popupMenu = PopupMenu(this@SupportActivity, anchorView)
        popupMenu.menuInflater.inflate(R.menu.profile_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            val itemId = item.itemId
            if (itemId == R.id.home) {
                startActivity(Intent(this@SupportActivity, UserProfileActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.my_profile) {
                startActivity(Intent(this@SupportActivity, MyProfileActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.guided_activities) {
                startActivity(Intent(this@SupportActivity, GuidedActivitiesActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.view_clubs) {
                startActivity(Intent(this@SupportActivity, ViewClubsActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.support) {
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.logout) {
                startActivity(Intent(this@SupportActivity, MainActivity::class.java))
                finish()
                return@OnMenuItemClickListener true
            }
            false
        })

        popupMenu.show()
    }
}
