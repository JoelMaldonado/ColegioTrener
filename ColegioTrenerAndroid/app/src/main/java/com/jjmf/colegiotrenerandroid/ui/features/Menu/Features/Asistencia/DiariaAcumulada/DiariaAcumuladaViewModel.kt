package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.DiariaAcumulada

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Asistencia
import com.jjmf.colegiotrenerandroid.domain.model.Inasistencia
import com.jjmf.colegiotrenerandroid.domain.repository.AsistenciaRepository
import com.kizitonwose.calendar.core.atStartOfMonth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DiariaAcumuladaViewModel @Inject constructor(
    private val repository: AsistenciaRepository
) : ViewModel() {

    var currentMonth = mutableStateOf<LocalDate>(LocalDate.now())

    var list by mutableStateOf<List<Inasistencia>>(emptyList())
    var asistencia by mutableStateOf<Asistencia?>(null)
    var ctacli by mutableStateOf("")

    var isLoading by mutableStateOf(false)

    fun getTotalMes(fecha: LocalDate, ctacli: String) {
        viewModelScope.launch {
            try {
                val res = repository.totalMes(
                    year = fecha.year.toString(),
                    month = fecha.monthValue.toString(),
                    ctacli = ctacli
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

    fun getList(year: String, month: String, ctacli: String) {
        viewModelScope.launch {
            isLoading = true
            delay(500)
            try {
                val res = repository.listarInasistenciasPorAlumno(
                    year = year,
                    month = month,
                    ctacli
                )
                when (res) {
                    is Result.Correcto -> {
                        list = res.datos ?: emptyList()
                    }

                    is Result.Error -> Log.d("tagito", res.mensaje.toString())
                }
            } catch (e: Exception) {
                Log.d("tagito", e.message.toString())
            } finally {
                isLoading = false
            }
        }
    }
}