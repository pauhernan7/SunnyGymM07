package com.example.sunnygym

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ReservasActivty : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var reservaAdapter: ReservaAdapter
    private val reservas = mutableListOf<Reserva>()
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas_activity)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        apiService = RetrofitClient.getApiService()

        reservaAdapter = ReservaAdapter(
            reservas,
            object : ReservaAdapter.OnEliminarClickListener {
                override fun onEliminarClick(id: Int) {
                    eliminarReserva(id)
                }
            },
            object : ReservaAdapter.OnEditarClickListener {
                override fun onEditarClick(id: Int) {
                    editarReserva(id)
                }
            }
        )
        recyclerView.adapter = reservaAdapter

        cargarReservas()

        val btnIrPerfil = findViewById<Button>(R.id.btnIrPerfil)
        btnIrPerfil.setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }
    }

    private fun cargarReservas() {
        lifecycleScope.launch {
            try {
                val response = apiService.getReservas()
                if (response.isSuccessful) {
                    response.body()?.let {
                        reservas.clear()
                        reservas.addAll(it)
                        reservaAdapter.notifyDataSetChanged()
                    }
                } else {
                    Log.e("ReservasActivty", "Error al cargar reservas: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ReservasActivty", "Error en la conexión: ${e.message}")
            }
        }
    }

    private fun eliminarReserva(id: Int) {
        lifecycleScope.launch {
            try {
                val response = apiService.deleteReserva(id)
                if (response.isSuccessful) {
                    reservas.removeIf { it.id == id }
                    reservaAdapter.notifyDataSetChanged()
                } else {
                    Log.e("ReservasActivty", "Error al eliminar reserva: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ReservasActivty", "Error en la conexión: ${e.message}")
            }
        }
    }

    private fun editarReserva(id: Int) {
        lifecycleScope.launch {
            try {
                val response = apiService.getClasesDisponibles()
                if (response.isSuccessful) {
                    response.body()?.let {
                        mostrarDialogoEditar(id, it)
                    }
                } else {
                    Log.e("ReservasActivty", "Error al obtener clases disponibles")
                }
            } catch (e: Exception) {
                Log.e("ReservasActivty", "Error en la conexión: ${e.message}")
            }
        }
    }

    private fun mostrarDialogoEditar(reservaId: Int, clasesDisponibles: List<String>) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona una nueva clase")
        val opciones = clasesDisponibles.toTypedArray()

        builder.setItems(opciones) { _, which ->
            actualizarReserva(reservaId, opciones[which])
        }
        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

    private fun actualizarReserva(id: Int, nuevaClase: String) {
        lifecycleScope.launch {
            try {
                val nuevaReserva = Reserva().apply {
                    this.id = id
                    actividadName = nuevaClase
                }

                val response = apiService.updateReserva(id, nuevaReserva)
                if (response.isSuccessful) {
                    cargarReservas()
                    Toast.makeText(this@ReservasActivty, "Reserva actualizada", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("ReservasActivty", "Error al actualizar reserva: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("ReservasActivty", "Error en la conexión: ${e.message}")
            }
        }
    }
}