package com.example.sunnygym

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewClubsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_clubs)

        val clubsList: MutableList<Clubs> = ArrayList()
        clubsList.add(Clubs("Meridiana", R.drawable.meridiana))
        clubsList.add(Clubs("Can Dragó", R.drawable.candrago))
        clubsList.add(Clubs("Diagonal", R.drawable.diagonal))

        // Configurar RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewClubs)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ClubsAdapter(this, clubsList)
    }

    /**
     * Método para mostrar el menú emergente
     *
     * @param anchorView Vista desde la cual se despliega el menú
     */
    private fun showPopupMenu(anchorView: View) {
        val popupMenu = PopupMenu(this@ViewClubsActivity, anchorView)
        popupMenu.menuInflater.inflate(R.menu.profile_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            val itemId = item.itemId
            if (itemId == R.id.home) {
                startActivity(Intent(this@ViewClubsActivity, UserProfileActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.my_profile) {
                startActivity(Intent(this@ViewClubsActivity, MyProfileActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.guided_activities) {
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.view_clubs) {
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.support) {
                startActivity(Intent(this@ViewClubsActivity, SupportActivity::class.java))
                return@OnMenuItemClickListener true
            } else if (itemId == R.id.logout) {
                startActivity(Intent(this@ViewClubsActivity, MainActivity::class.java))
                finish()
                return@OnMenuItemClickListener true
            }
            false
        })

        popupMenu.show()
    } // Crear lista de actividades
}
