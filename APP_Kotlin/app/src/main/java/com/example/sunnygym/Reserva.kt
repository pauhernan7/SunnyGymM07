package com.example.sunnygym

import com.google.gson.annotations.SerializedName

data class Reserva(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("actividad_name") var actividadName: String? = null
)