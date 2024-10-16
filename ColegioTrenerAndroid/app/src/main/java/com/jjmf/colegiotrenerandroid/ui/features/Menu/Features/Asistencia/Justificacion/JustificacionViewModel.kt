package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Asistencia
import com.jjmf.colegiotrenerandroid.domain.model.Inasistencia
import com.jjmf.colegiotrenerandroid.domain.model.Justificacion
import com.jjmf.colegiotrenerandroid.domain.model.Razones
import com.jjmf.colegiotrenerandroid.domain.repository.CarnetRepository
import com.jjmf.colegiotrenerandroid.domain.repository.JustificacionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class JustificacionViewModel @Inject constructor(
    private val repository: JustificacionRepository
) : ViewModel()  {

    var ctacli by mutableStateOf("")
    var currentMonth = mutableStateOf<LocalDate>(LocalDate.now())
    var list by mutableStateOf<List<Justificacion>>(emptyList())
    var listRazones by mutableStateOf<List<Razones>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var success by mutableStateOf<String?>(null)

    fun getList(ctacli: String,fecha: LocalDate) {
        viewModelScope.launch {
            isLoading = true
            delay(500)
            try {
                val res = repository.getJustificaciones(
                    ctaCli = ctacli,
                    year = fecha.year.toString()
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

    fun getListRazones() {
        viewModelScope.launch {
            isLoading = true
            delay(500)
            try {
                val res = repository.getRazones()
                when (res) {
                    is Result.Correcto -> {
                        listRazones = res.datos ?: emptyList()
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

    fun grabarJustificacion(fecha: String,ctacli: String,idRazon:String,comentario:String,fileName:String) {
        viewModelScope.launch {
            isLoading = true
            delay(500)
            try {
                val res = repository.saveJustificacion(
                    fecha = fecha,
                    ctaCli = ctacli,
                    idRazon = idRazon,
                    comentario = comentario,
                    fileName = fileName
                )
                when (res) {
                    is Result.Correcto -> {
                        success = res.datos ?: ""
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

    fun clearSuccess() {
        success = null
    }

}