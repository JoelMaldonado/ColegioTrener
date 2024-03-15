package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Autorizacion
import com.jjmf.colegiotrenerandroid.domain.model.EstadoAutorizacion
import com.jjmf.colegiotrenerandroid.domain.model.Hijo
import com.jjmf.colegiotrenerandroid.domain.repository.AutorizacionRepository
import com.jjmf.colegiotrenerandroid.domain.repository.PersonaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutorizacionesViewModel @Inject constructor(
    private val repository: AutorizacionRepository
) : ViewModel() {

    var estado by mutableStateOf(Estado.Activo)
    var list by mutableStateOf<List<Autorizacion>>(emptyList())
    var listEstados by mutableStateOf<List<EstadoAutorizacion>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    init {
        listarAutorizaciones(estado)
        listarEstados()
    }

    fun listarEstados(){
        viewModelScope.launch {
            try {
                val res = repository.estado()
                when(res){
                    is Result.Correcto -> listEstados = res.datos ?: emptyList()
                    is Result.Error -> error = res.mensaje
                }
            }catch (e:Exception){
                error = e.message
            }
        }
    }

    fun listarAutorizaciones(estado: Estado) {
        viewModelScope.launch {
            try {
                when (val res = repository.listarAutorizaciones(estado)) {
                    is Result.Correcto -> list = res.datos ?: emptyList()
                    is Result.Error -> res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun setError(delay: Boolean) {
        viewModelScope.launch {
            if (delay) {
                delay(1500)
            }
            error = null
        }
    }

}