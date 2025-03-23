package com.example.sunnygym

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservaAdapter(
    private val reservas: List<Reserva>,
    private val eliminarClickListener: OnEliminarClickListener?,
    private val editarClickListener: OnEditarClickListener?
) : RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva, parent, false)
        return ReservaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val reserva = reservas[position]
        holder.textActividadName.text = reserva.actividadName
        holder.actualizarImagen(reserva.actividadName)

        holder.btnEliminar.setOnClickListener {
            eliminarClickListener?.onEliminarClick(reserva.id)
        }

        holder.btnEditar.setOnClickListener {
            editarClickListener?.onEditarClick(reserva.id)
        }
    }

    override fun getItemCount(): Int = reservas.size

    class ReservaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textActividadName: TextView = itemView.findViewById(R.id.textActividadName)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditar)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun actualizarImagen(actividad: String?) {
            when (actividad?.lowercase()) {
                "zumba" -> imageView.setImageResource(R.drawable.zumba_image)
                "body combat" -> imageView.setImageResource(R.drawable.body_combat_image)
                "body pump" -> imageView.setImageResource(R.drawable.body_pump_image)
            }
        }
    }

    interface OnEliminarClickListener {
        fun onEliminarClick(id: Int)
    }

    interface OnEditarClickListener {
        fun onEditarClick(id: Int)
    }
}