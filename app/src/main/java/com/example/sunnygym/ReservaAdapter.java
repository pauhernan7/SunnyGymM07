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

    public ReservaAdapter(List<Reserva> reservas, OnEliminarClickListener listener) {
        this.reservas = reservas;
        this.eliminarClickListener = listener;
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

        // Evento para eliminar la reserva
        holder.btnEliminar.setOnClickListener(v -> {
            if (eliminarClickListener != null) {
                eliminarClickListener.onEliminarClick(reserva.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservas.size();
    }

    public static class ReservaViewHolder extends RecyclerView.ViewHolder {
        TextView textActividadName;
        Button btnEliminar;
        ImageView imageView;

        public ReservaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textActividadName = itemView.findViewById(R.id.textActividadName);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    public interface OnEliminarClickListener {
        void onEliminarClick(int id);
    }
}
