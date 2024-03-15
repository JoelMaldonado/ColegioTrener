package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Pendientes

import android.util.Log
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jjmf.colegiotrenerandroid.util.format
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TareasPendientesViewModel @Inject constructor(

) : ViewModel() {
    var fecha by mutableStateOf(Date())

    fun monthBefore() {
        val calendar = Calendar.getInstance()
        calendar.time = fecha
        calendar.add(Calendar.MONTH, -1)
        fecha = calendar.time
    }

    fun monthAfter() {
        val calendar = Calendar.getInstance()
        calendar.time = fecha
        calendar.add(Calendar.MONTH, 1)
        fecha = calendar.time
    }

    fun obtenerDiasPorSemana(fecha: Date): Map<String, List<Date>> {
        val calendar = Calendar.getInstance()
        calendar.time = fecha

        // Crear un mapa para almacenar los listados de fechas por día de la semana
        val diasPorSemana = mutableMapOf<String, MutableList<Date>>()
        val diasDeLaSemana = arrayOf("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado")

        // Inicializar listas vacías para cada día de la semana
        for (dia in diasDeLaSemana) {
            diasPorSemana[dia] = mutableListOf()
        }

        // Iterar a través de cada día del mes
        val mesActual = calendar.get(Calendar.MONTH)
        while (calendar.get(Calendar.MONTH) == mesActual) {
            val diaDeLaSemana = diasDeLaSemana[calendar.get(Calendar.DAY_OF_WEEK) - 1] // Restamos 1 porque el índice de días de la semana comienza en 1
            diasPorSemana[diaDeLaSemana]?.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return diasPorSemana
    }

    fun getDias(): List<Pair<Date ,List<Date?>>> {
        val listDiasSemana = listOf(
            Calendar.MONDAY,
            Calendar.TUESDAY,
            Calendar.WEDNESDAY,
            Calendar.THURSDAY,
            Calendar.FRIDAY,
            Calendar.SATURDAY,
            Calendar.SUNDAY
        )

        val calendar = Calendar.getInstance()

        // Obtener el primer día del mes actual
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val primerDiaDelMes = calendar.time

        val result = mutableListOf<Pair<Date, List<Date?>>>()

        listDiasSemana.forEach { diaSemana ->
            // Reiniciar el calendario al primer día del mes
            calendar.time = primerDiaDelMes

            // Avanzar hasta el día de la semana correspondiente
            while (calendar.get(Calendar.DAY_OF_WEEK) != diaSemana) {
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }

            // Agregar las fechas correspondientes a este día de la semana
            val mes = calendar.get(Calendar.MONTH)
            val diasDelMes = mutableListOf<Date?>()
            while (calendar.get(Calendar.MONTH) == mes) {
                diasDelMes.add(calendar.time)
                calendar.add(Calendar.DAY_OF_MONTH, 7)
            }

            result.add(Pair(primerDiaDelMes, diasDelMes))
        }

        return result
    }

    fun obtenerDiasDelMesConInicioEspecifico(diaInicio: Int): List<Date> {
        val calendar = Calendar.getInstance().apply {
            time = fecha
        }

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Ajustar el índice del mes a partir de 1

        // Mover al primer día de la semana especificado si el primer día del mes actual no es ese día
        while (calendar.get(Calendar.DAY_OF_WEEK) != diaInicio) {
            calendar.add(Calendar.DAY_OF_MONTH, -1)
        }

        val diasDelMes = mutableListOf<Date>()

        // Generar las fechas del mes
        while (calendar.get(Calendar.MONTH) + 1 == month) {
            diasDelMes.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return diasDelMes
    }

}