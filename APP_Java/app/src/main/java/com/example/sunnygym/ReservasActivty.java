package com.example.sunnygym;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservasActivty extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReservaAdapter reservaAdapter;
    private List<Reserva> reservas = new ArrayList<>();
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas_activity);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getApiService();
        reservaAdapter = new ReservaAdapter(reservas, this::eliminarReserva, this::editarReserva);
        recyclerView.setAdapter(reservaAdapter);

        cargarReservas();

        Button btnIrPerfil = findViewById(R.id.btnIrPerfil);
        btnIrPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(ReservasActivty.this, UserProfileActivity.class);
            startActivity(intent);
        });
    }

    private void cargarReservas() {
        apiService.getReservas().enqueue(new Callback<List<Reserva>>() {
            @Override
            public void onResponse(Call<List<Reserva>> call, Response<List<Reserva>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    reservas.clear();
                    reservas.addAll(response.body());
                    reservaAdapter.notifyDataSetChanged();
                } else {
                    Log.e("ReservasActivty", "Error al cargar reservas: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Reserva>> call, Throwable t) {
                Log.e("ReservasActivty", "Error en la conexión: " + t.getMessage());
            }
        });
    }

    private void eliminarReserva(int id) {
        apiService.deleteReserva(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    reservas.removeIf(r -> r.getId() == id);
                    reservaAdapter.notifyDataSetChanged();
                } else {
                    Log.e("ReservasActivty", "Error al eliminar reserva: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("ReservasActivty", "Error en la conexión: " + t.getMessage());
            }
        });
    }

    private void editarReserva(int id) {
        apiService.getClasesDisponibles().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mostrarDialogoEditar(id, response.body());
                } else {
                    Log.e("ReservasActivty", "Error al obtener clases disponibles");
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("ReservasActivty", "Error en la conexión: " + t.getMessage());
            }
        });
    }

    private void mostrarDialogoEditar(int reservaId, List<String> clasesDisponibles) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona una nueva clase");
        String[] opciones = clasesDisponibles.toArray(new String[0]);

        builder.setItems(opciones, (dialog, which) -> actualizarReserva(reservaId, opciones[which]));
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void actualizarReserva(int id, String nuevaClase) {
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setId(id);
        nuevaReserva.setActividadName(nuevaClase);

        apiService.updateReserva(id, nuevaReserva).enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                if (response.isSuccessful()) {
                    cargarReservas();
                    Toast.makeText(ReservasActivty.this, "Reserva actualizada", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("ReservasActivty", "Error al actualizar reserva: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Log.e("ReservasActivty", "Error en la conexión: " + t.getMessage());
            }
        });
    }
}