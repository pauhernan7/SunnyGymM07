package com.example.sunnygym;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder> {
    private List<Reserva> reservas;
    private OnEliminarClickListener eliminarClickListener;
    private OnEditarClickListener editarClickListener;

    public ReservaAdapter(List<Reserva> reservas, OnEliminarClickListener eliminarListener, OnEditarClickListener editarListener) {
        this.reservas = reservas;
        this.eliminarClickListener = eliminarListener;
        this.editarClickListener = editarListener;
    }

    @NonNull
    @Override
    public ReservaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reserva, parent, false);
        return new ReservaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservaViewHolder holder, int position) {
        Reserva reserva = reservas.get(position);
        holder.textActividadName.setText(reserva.getActividadName());
        holder.actualizarImagen(reserva.getActividadName());

        // Evento para eliminar la reserva
        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarClickListener != null) {
                eliminarClickListener.onEliminarClick(reserva.getId());
            }
        });

        // Evento para editar la reserva
        holder.btnEditar.setOnClickListener(v -> {
            if (editarClickListener != null) {
                editarClickListener.onEditarClick(reserva.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    public static class ReservaViewHolder extends RecyclerView.ViewHolder {
        TextView textActividadName;
        Button btnEliminar, btnEditar;
        ImageView imageView;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            textActividadName = itemView.findViewById(R.id.textActividadName);
            imageView = itemView.findViewById(R.id.imageView);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }

        // Método para actualizar la imagen según la actividad
        public void actualizarImagen(String actividad) {
            if (actividad.equalsIgnoreCase("zumba")) {
                imageView.setImageResource(R.drawable.zumba_image);
            } else if (actividad.equalsIgnoreCase("body combat")) {
                imageView.setImageResource(R.drawable.body_combat_image);
            } else if (actividad.equalsIgnoreCase("body pump")) {
                imageView.setImageResource(R.drawable.body_pump_image);
            }
        }
    }

    public interface OnEliminarClickListener {
        void onEliminarClick(int id);
    }

    public interface OnEditarClickListener {
        void onEditarClick(int id);
    }
}
