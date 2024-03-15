package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.DiariaAcumulada

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Asistencia
import com.jjmf.colegiotrenerandroid.domain.model.Inasistencia
import com.jjmf.colegiotrenerandroid.domain.repository.AsistenciaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DiariaAcumuladaViewModel @Inject constructor(
    private val repository: AsistenciaRepository
) : ViewModel() {

    val daysOfWeek = listOf("Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom")
    var currentMonth by mutableStateOf<LocalDate>(LocalDate.now())

    var list by mutableStateOf<List<Inasistencia>>(emptyList())
    var asistencia by mutableStateOf<Asistencia?>(null)

    init {
        getList(currentMonth)
        getTotalMes(currentMonth)
    }

    fun getTotalMes(fecha: LocalDate) {
        viewModelScope.launch {
            try {
                val res = repository.totalMes(
                    year = fecha.year.toString(),
                    month = fecha.monthValue.toString()
                )
                when (res) {
                    is Result.Correcto -> {
                        Log.d("tagitofr", res.datos.toString())
                        asistencia = res.datos
                    }
                    is Result.Error -> Log.d("tagito", res.mensaje.toString())
                }
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
            }
        }
    }

    fun getList(fecha: LocalDate) {
        viewModelScope.launch {
            try {
                val res = repository.listarInasistenciasPorAlumno(
                    year = fecha.year.toString(),
                    month = fecha.monthValue.toString()
                )
                when (res) {
                    is Result.Correcto -> {
                        list = res.datos ?: emptyList()
                    }
                    is Result.Error -> Log.d("tagito", res.mensaje.toString())
                }
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
            }
        }
    }
}