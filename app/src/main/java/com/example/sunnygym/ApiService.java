package com.example.sunnygym;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import java.util.List;

public interface ApiService {
    @GET("reservas")
    Call<List<Reserva>> getReservas();

    @POST("reservas")
    Call<Reserva> addReserva(@Body Reserva reserva);

    @DELETE("reservas/{id}")
    Call<Void> deleteReserva(@Path("id") int id);

    // Nuevo endpoint para verificar la conexión con la API
    @GET("ping")
    Call<PingResponse> ping();
}
