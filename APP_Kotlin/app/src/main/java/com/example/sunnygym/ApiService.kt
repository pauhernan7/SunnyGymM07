package com.example.sunnygym


import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("reservas")
    fun getReservas(): Call<List<Reserva>>

    @POST("reservas")
    fun addReserva(@Body reserva: Reserva): Call<Reserva>

    @DELETE("reservas/{id}")
    fun deleteReserva(@Path("id") id: Int): Call<Void>

    @PUT("reservas/{id}")
    fun updateReserva(@Path("id") id: Int, @Body reserva: Reserva): Call<Reserva>

    @GET("clases_disponibles")
    fun getClasesDisponibles(): Call<List<String>>
}