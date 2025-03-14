package com.example.sunnygym;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder> {

    private List<Activity> activities;
    private Context context;
    private ApiService apiService;

    public ActivitiesAdapter(Context context, List<Activity> activities) {
        this.context = context;
        this.activities = activities;
        this.apiService = RetrofitClient.getApiService();
    }

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
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        Activity activity = activities.get(position);

        holder.activityImage.setImageResource(activity.getImageResId());
        holder.activityName.setText(activity.getName());

        if (activity.isDisponibilitat()) {
            holder.btnSignUp.setBackgroundColor(Color.GREEN);
        } else {
            holder.btnSignUp.setBackgroundColor(Color.RED);
        }

        holder.btnSignUp.setOnClickListener(v -> {
            Reserva nuevaReserva = new Reserva();
            nuevaReserva.setActividadName(activity.getName()); // Asegúrate de que esto se asigna correctamente

            Call<Reserva> call = apiService.addReserva(nuevaReserva);
            call.enqueue(new Callback<Reserva>() {
                @Override
                public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Reserva creada: " + activity.getName(), Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("ActivitiesAdapter", "Error al crear reserva: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Reserva> call, Throwable t) {
                    Log.e("ActivitiesAdapter", "Fallo en la conexión: " + t.getMessage());
                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return activities.size();
    }
}
