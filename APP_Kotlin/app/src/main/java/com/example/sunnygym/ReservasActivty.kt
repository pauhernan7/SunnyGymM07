package com.example.sunnygym

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        initViews()
        setupRecyclerView()
        cargarReservas()
        setupButtons()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        apiService = RetrofitClient.getApiService()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        reservaAdapter = ReservaAdapter(
            reservas,
            object : ReservaAdapter.OnEliminarClickListener {
                override fun onEliminarClick(id: Int) {
                    mostrarConfirmacionEliminar(id)
                }
            },
            object : ReservaAdapter.OnEditarClickListener {
                override fun onEditarClick(id: Int) {
                    editarReserva(id)
                }
            }
        )
        recyclerView.adapter = reservaAdapter
    }

    private fun setupButtons() {
        findViewById<Button>(R.id.btnIrPerfil).setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }

        findViewById<Button>(R.id.btnEstadisticas).setOnClickListener {
            if (reservas.isNotEmpty()) {
                startActivity(Intent(this, StatsActivity::class.java).apply {
                    putExtra("reservas", ArrayList(reservas))
                })
            } else {
                Toast.makeText(this, "No hay reservas para mostrar", Toast.LENGTH_SHORT).show()
            }
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
                    mostrarError("Error al cargar reservas: ${response.message()}")
                }
            } catch (e: Exception) {
                mostrarError("Error en la conexión: ${e.message}")
            }
        }
    }

    private fun mostrarConfirmacionEliminar(id: Int) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar eliminación")
            .setMessage("¿Estás seguro de que quieres eliminar esta reserva?")
            .setPositiveButton("Eliminar") { _, _ -> eliminarReserva(id) }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun eliminarReserva(id: Int) {
        lifecycleScope.launch {
            try {
                val response = apiService.deleteReserva(id)
                if (response.isSuccessful) {
                    reservas.removeIf { it.id == id }
                    reservaAdapter.notifyDataSetChanged()
                    Toast.makeText(this@ReservasActivty, "Reserva eliminada", Toast.LENGTH_SHORT).show()
                } else {
                    mostrarError("Error al eliminar reserva: ${response.message()}")
                }
            } catch (e: Exception) {
                mostrarError("Error en la conexión: ${e.message}")
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
                    mostrarError("Error al obtener clases disponibles")
                }
            } catch (e: Exception) {
                mostrarError("Error en la conexión: ${e.message}")
            }
        }
    }

    private fun mostrarDialogoEditar(reservaId: Int, clasesDisponibles: List<String>) {
        AlertDialog.Builder(this)
            .setTitle("Selecciona una nueva clase")
            .setItems(clasesDisponibles.toTypedArray()) { _, which ->
                actualizarReserva(reservaId, clasesDisponibles[which])
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun actualizarReserva(id: Int, nuevaClase: String) {
        lifecycleScope.launch {
            try {
                val response = apiService.updateReserva(id, Reserva(id = id, actividadName = nuevaClase))
                if (response.isSuccessful) {
                    cargarReservas()
                    Toast.makeText(this@ReservasActivty, "Reserva actualizada", Toast.LENGTH_SHORT).show()
                } else {
                    mostrarError("Error al actualizar reserva: ${response.message()}")
                }
            } catch (e: Exception) {
                mostrarError("Error en la conexión: ${e.message}")
            }
        }
    }

    private fun mostrarError(mensaje: String) {
        Log.e("ReservasActivty", mensaje)
        Toast.makeText(this, "Ocurrió un error. Intenta nuevamente.", Toast.LENGTH_SHORT).show()
    }
}