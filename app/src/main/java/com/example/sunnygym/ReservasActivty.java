// ReservasActivty.java
package com.example.sunnygym;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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
        setContentView(R.layout.activity_reservas_activity); // Corrección del nombre

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reservaAdapter = new ReservaAdapter(reservas, id -> eliminarReserva(id));
        recyclerView.setAdapter(reservaAdapter);

        apiService = RetrofitClient.getApiService();
        cargarReservas();

        Button btnIrPerfil = findViewById(R.id.btnIrPerfil);
        btnIrPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(ReservasActivty.this, UserProfileActivity.class);
            startActivity(intent);
        });

    }

    private void cargarReservas() {
        Call<List<Reserva>> call = apiService.getReservas();
        call.enqueue(new Callback<List<Reserva>>() {
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
        Call<Void> call = apiService.deleteReserva(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < reservas.size(); i++) {
                        if (reservas.get(i).getId() == id) {
                            reservas.remove(i);
                            reservaAdapter.notifyDataSetChanged(); // Corrección aquí
                            break;
                        }
                    }
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
}
