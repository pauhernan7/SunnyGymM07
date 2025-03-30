package com.example.sunnygym

import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("reservas")
    suspend fun getReservas(): Response<List<Reserva>>

    @POST("reservas")
    suspend fun addReserva(@Body reserva: Reserva): Response<Reserva>

    @DELETE("reservas/{id}")
    suspend fun deleteReserva(@Path("id") id: Int): Response<Void>

    @PUT("reservas/{id}")
    suspend fun updateReserva(@Path("id") id: Int, @Body reserva: Reserva): Response<Reserva>

    @GET("clases_disponibles")
    suspend fun getClasesDisponibles(): Response<List<String>>
}