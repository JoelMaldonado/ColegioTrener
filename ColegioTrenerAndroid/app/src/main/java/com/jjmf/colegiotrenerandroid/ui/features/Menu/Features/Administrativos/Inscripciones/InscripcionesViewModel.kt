package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Inscripcion
import com.jjmf.colegiotrenerandroid.domain.repository.InscripcionesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InscripcionesViewModel @Inject constructor(
    private val repository: InscripcionesRepository
) : ViewModel() {

    var ctacli by mutableStateOf<String?>(null)
    var listInscripcion by mutableStateOf<List<Inscripcion>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    var alert by mutableStateOf(false)

    fun getListInscripciones(ctacli: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                listInscripcion = emptyList()
                when (val res = repository.getListInscripciones(ctacli)) {
                    is Result.Correcto -> {
                        listInscripcion = res.datos ?: emptyList()
                        alert = res.datos?.firstOrNull()?.inscripcionbloqueo == "0"
                    }

                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

}