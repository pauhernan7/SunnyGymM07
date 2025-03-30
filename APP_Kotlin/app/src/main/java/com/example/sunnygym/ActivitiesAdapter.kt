package com.example.sunnygym

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ActivitiesAdapter(
    private val context: Context,
    private val activities: List<Activity>
) : RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder>() {

    private val apiService: ApiService = RetrofitClient.getApiService()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        holder.activityImage.setImageResource(activity.imageResId)
        holder.activityName.text = activity.name

        holder.btnDispo.setBackgroundColor(
            if (activity.disponibilitat) Color.GREEN else Color.RED
        )

        holder.btnSignUp.setOnClickListener {
            if (context is AppCompatActivity) {
                context.lifecycleScope.launch {
                    try {
                        val nuevaReserva = Reserva(actividadName = activity.name)
                        val response = apiService.addReserva(nuevaReserva)

                        if (response.isSuccessful) {
                            Toast.makeText(context, "Reserva creada: ${activity.name}", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.e("ActivitiesAdapter", "Error al crear reserva: ${response.code()} - ${response.message()}")
                            Toast.makeText(context, "Error: Actividad No disponible", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        Log.e("ActivitiesAdapter", "Fallo en la conexión: ${e.message}")
                        Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = activities.size

    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityImage: ImageView = itemView.findViewById(R.id.activityImage)
        val activityName: TextView = itemView.findViewById(R.id.activityName)
        val btnSignUp: Button = itemView.findViewById(R.id.btnSignUp)
        val btnDispo: Button = itemView.findViewById(R.id.disponibilitat)
    }
}