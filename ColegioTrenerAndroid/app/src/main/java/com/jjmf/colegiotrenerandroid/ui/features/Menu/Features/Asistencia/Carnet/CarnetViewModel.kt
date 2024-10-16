package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Carnet

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Autorizacion
import com.jjmf.colegiotrenerandroid.domain.model.Carnet
import com.jjmf.colegiotrenerandroid.domain.model.EstadoAutorizacion
import com.jjmf.colegiotrenerandroid.domain.repository.AsistenciaRepository
import com.jjmf.colegiotrenerandroid.domain.repository.CarnetRepository
import com.jjmf.colegiotrenerandroid.domain.repository.InscripcionesRepository
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.Estado
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarnetViewModel @Inject constructor(
    private val repository: CarnetRepository
) : ViewModel()  {

    var ctacli by mutableStateOf("")

    var estado by mutableStateOf(Estado.Activo)
    var list by mutableStateOf<Carnet?>(null)
    var isLoadingEstados by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun obtenerCarnet(ctaCli:String) {
        viewModelScope.launch {
            try {
                isLoadingEstados = true
                when (val res = repository.getCarnet(ctaCli)) {
                    is Result.Correcto -> {
                        list = res.datos
                    }

                    is Result.Error -> res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoadingEstados = false
            }
        }
    }
}