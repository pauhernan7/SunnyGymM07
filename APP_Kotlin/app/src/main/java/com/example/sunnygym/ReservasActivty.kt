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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservasActivty : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var reservaAdapter: ReservaAdapter? = null
    private val reservas: MutableList<Reserva> = ArrayList()
    private var apiService: ApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas_activity)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        apiService = RetrofitClient.getApiService()

        // Pasamos las funciones con el tipo correcto
        reservaAdapter = ReservaAdapter(
            reservas,
            object : ReservaAdapter.OnEliminarClickListener {  // Usamos un objeto anónimo para OnEliminarClickListener
                override fun onEliminarClick(id: Int) {
                    eliminarReserva(id)
                }
            },
            object : ReservaAdapter.OnEditarClickListener {  // Lo mismo para OnEditarClickListener
                override fun onEditarClick(id: Int) {
                    editarReserva(id)
                }
            }
        )
        recyclerView?.adapter = reservaAdapter

        cargarReservas()

        val btnIrPerfil = findViewById<Button>(R.id.btnIrPerfil)
        btnIrPerfil.setOnClickListener { v: View? ->
            val intent = Intent(this@ReservasActivty, UserProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun cargarReservas() {
        apiService?.getReservas()?.enqueue(object : Callback<List<Reserva>> {
            override fun onResponse(
                call: Call<List<Reserva>>,
                response: Response<List<Reserva>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    reservas.clear()
                    reservas.addAll(response.body()!!)
                    reservaAdapter?.notifyDataSetChanged()
                } else {
                    Log.e("ReservasActivty", "Error al cargar reservas: " + response.message())
                }
            }

            override fun onFailure(call: Call<List<Reserva>>, t: Throwable) {
                Log.e("ReservasActivty", "Error en la conexión: " + t.message)
            }
        })
    }

    // Esta función eliminarReserva ahora implementa correctamente el tipo de la interfaz.
    private fun eliminarReserva(id: Int) {
        apiService?.deleteReserva(id)?.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.isSuccessful) {
                    reservas.removeIf { r: Reserva -> r.id == id }
                    reservaAdapter?.notifyDataSetChanged()
                } else {
                    Log.e("ReservasActivty", "Error al eliminar reserva: " + response.message())
                }
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("ReservasActivty", "Error en la conexión: " + t.message)
            }
        })
    }

    private fun editarReserva(id: Int) {
        apiService?.getClasesDisponibles()?.enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    mostrarDialogoEditar(id, response.body())
                } else {
                    Log.e("ReservasActivty", "Error al obtener clases disponibles")
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("ReservasActivty", "Error en la conexión: " + t.message)
            }
        })
    }

    private fun mostrarDialogoEditar(reservaId: Int, clasesDisponibles: List<String>?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona una nueva clase")
        val opciones = clasesDisponibles!!.toTypedArray()

        builder.setItems(opciones) { dialog: DialogInterface?, which: Int ->
            actualizarReserva(reservaId, opciones[which])
        }
        builder.setNegativeButton("Cancelar") { dialog: DialogInterface, which: Int -> dialog.dismiss() }
        builder.show()
    }

    private fun actualizarReserva(id: Int, nuevaClase: String?) {
        val nuevaReserva = Reserva()
        nuevaReserva.id = id
        nuevaReserva.actividadName = nuevaClase

        apiService?.updateReserva(id, nuevaReserva)?.enqueue(object : Callback<Reserva?> {
            override fun onResponse(call: Call<Reserva?>, response: Response<Reserva?>) {
                if (response.isSuccessful) {
                    cargarReservas()
                    Toast.makeText(this@ReservasActivty, "Reserva actualizada", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Log.e("ReservasActivty", "Error al actualizar reserva: " + response.message())
                }
            }

            override fun onFailure(call: Call<Reserva?>, t: Throwable) {
                Log.e("ReservasActivty", "Error en la conexión: " + t.message)
            }
        })
    }
}
