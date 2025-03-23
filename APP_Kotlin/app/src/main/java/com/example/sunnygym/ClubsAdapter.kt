package com.example.sunnygym

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ClubsAdapter // Constructor
    (// Contexto para manejar eventos
    private val context: Context, // Lista de clubs
    private val clubsList: List<Clubs>
) : RecyclerView.Adapter<ClubsAdapter.ActivityViewHolder>() {
    // ViewHolder: vincula las vistas del ítem
    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var activityImage: ImageView = itemView.findViewById(R.id.activityImage)
        var activityName: TextView = itemView.findViewById(R.id.activityName)
        var btnSignUp: Button = itemView.findViewById(R.id.btnSignUp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        // Inflar el diseño de cada ítem
        val view = LayoutInflater.from(context).inflate(R.layout.club_item, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        // Obtener la actividad actual
        val club = clubsList[position]

        // Configurar las vistas con los datos
        holder.activityImage.setImageResource(club.imageResId)
        holder.activityName.text = club.name

        holder.btnSignUp.setOnClickListener { v: View? ->
            Toast.makeText(
                context, "Te has apuntado a " + club.name, Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return clubsList.size // Tamaño de la lista
    }
}
