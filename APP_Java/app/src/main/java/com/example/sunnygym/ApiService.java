package com.example.sunnygym;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import java.util.List;

public interface ApiService {
    @GET("reservas")
    Call<List<Reserva>> getReservas();

    @POST("reservas")
    Call<Reserva> addReserva(@Body Reserva reserva);

    @DELETE("reservas/{id}")
    Call<Void> deleteReserva(@Path("id") int id);

    @PUT("reservas/{id}")
    Call<Reserva> updateReserva(@Path("id") int id, @Body Reserva reserva);

    @GET("clases_disponibles")
    Call<List<String>> getClasesDisponibles();


}
