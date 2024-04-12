package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Pendientes

import android.util.Log
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.EstadoCalPendiente
import com.jjmf.colegiotrenerandroid.domain.model.EstadoCalPendienteDia
import com.jjmf.colegiotrenerandroid.domain.repository.TareaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TareasPendientesViewModel @Inject constructor(
    private val repository: TareaRepository
) : ViewModel() {

    var ctacli by mutableStateOf("")
    var list by mutableStateOf<List<EstadoCalPendiente>>(emptyList())
    var listDia by mutableStateOf<List<EstadoCalPendienteDia>?>(null)
    var error by mutableStateOf<String?>(null)

    fun listarDatosCalendario(
        anio: String,
        mes: String
    ) {
        viewModelScope.launch {
            try {
                val res = repository.getEstadoCalPendiente(
                    ctacli = ctacli,
                    anio = anio,
                    mes = mes
                )
                when (res) {
                    is Result.Correcto -> {
                        list = res.datos ?: emptyList()
                    }

                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

    fun selectDia(date: LocalDate) {
        viewModelScope.launch {
            try {
                val res = repository.getEstadoCalPendienteDia(
                    ctacli = ctacli,
                    anio = date.year.toString(),
                    mes = date.monthValue.toString(),
                    dia = date.dayOfMonth.toString()
                )
                when (res) {
                    is Result.Correcto -> {
                        listDia = res.datos
                    }

                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }
}