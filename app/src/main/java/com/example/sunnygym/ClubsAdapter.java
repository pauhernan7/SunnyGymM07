package com.example.sunnygym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.ActivityViewHolder> {

    private List<Activity> activities; // Lista de actividades
    private Context context; // Contexto para manejar eventos

    // Constructor
    public ClubsAdapter(Context context, List<Activity> activities) {
        this.context = context;
        this.activities = activities;
    }

    // ViewHolder: vincula las vistas del ítem
    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        ImageView activityImage;
        TextView activityName;
        Button btnSignUp;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            activityImage = itemView.findViewById(R.id.activityImage);
            activityName = itemView.findViewById(R.id.activityName);
            btnSignUp = itemView.findViewById(R.id.btnSignUp);
        }
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de cada ítem
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        // Obtener la actividad actual
        Activity activity = activities.get(position);

        // Configurar las vistas con los datos
        holder.activityImage.setImageResource(activity.getImageResId());
        holder.activityName.setText(activity.getName());

        // Evento para el botón "Apuntarse"
        holder.btnSignUp.setOnClickListener(v ->
                Toast.makeText(context, "Te has apuntado a " + activity.getName(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return activities.size(); // Tamaño de la lista
    }
}
