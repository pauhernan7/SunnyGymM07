package com.example.sunnygym;

import android.os.Bundle;
import android.util.Log;
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
        setContentView(R.layout.activity_reservas_activty);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener instancia de ApiService
        apiService = RetrofitClient.getApiService();

        // Cargar reservas desde la API
        cargarReservas();
    }

    private void cargarReservas() {
        Call<List<Reserva>> call = apiService.getReservas();
        call.enqueue(new Callback<List<Reserva>>() {
            @Override
            public void onResponse(Call<List<Reserva>> call, Response<List<Reserva>> response) {
                if (response.isSuccessful()) {
                    // Actualizar la lista de reservas
                    reservas = response.body();
                    // Configurar el Adapter
                    reservaAdapter = new ReservaAdapter(reservas, id -> eliminarReserva(id));
                    recyclerView.setAdapter(reservaAdapter);
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
                    // Recargar la lista después de eliminar
                    cargarReservas();
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