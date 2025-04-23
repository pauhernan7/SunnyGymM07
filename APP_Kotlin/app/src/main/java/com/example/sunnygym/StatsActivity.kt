package com.example.sunnygym

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunnygym.R
import com.example.sunnygym.Reserva
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class StatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val reservas = (intent.getSerializableExtra("reservas") as? ArrayList<Reserva>) ?: arrayListOf()
        setupPieChart(reservas)
    }

    private fun setupPieChart(reservas: List<Reserva>) {
        val pieChart: PieChart = findViewById(R.id.pieChart)

        if (reservas.isEmpty()) {
            pieChart.setNoDataText("No tienes reservas registradas")
            pieChart.setNoDataTextColor(Color.GRAY)
            return
        }

        // Procesar datos: Contar reservas por actividad
        val conteoActividades = reservas
            .groupBy { it.actividadName ?: "Sin nombre" }
            .mapValues { it.value.size }

        // Crear entradas para el gráfico
        val entries = conteoActividades.map { (actividad, count) ->
            PieEntry(count.toFloat(), actividad)
        }

        // Configurar el DataSet
        val dataSet = PieDataSet(entries, "").apply {
            colors = listOf(
                Color.parseColor("#FF5722"),  // Naranja
                Color.parseColor("#4CAF50"),  // Verde
                Color.parseColor("#2196F3"),  // Azul
                Color.parseColor("#9C27B0"), // Morado
                Color.parseColor("#FFEB3B")   // Amarillo
            )
            valueTextColor = Color.BLACK
            valueTextSize = 14f
            setDrawValues(true)
        }

        // Configurar el gráfico
        pieChart.apply {
            data = PieData(dataSet)
            description.isEnabled = false
            setEntryLabelColor(Color.BLACK)
            setUsePercentValues(true)
            centerText = "Tus reservas"
            setCenterTextSize(16f)
            holeRadius = 40f
            transparentCircleRadius = 45f
            animateY(1000)
            legend.isEnabled = true
            invalidate()
        }
    }
}