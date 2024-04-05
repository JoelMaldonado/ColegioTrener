package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.HijosDatosAdmin

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.repository.PersonaRepository
import com.jjmf.colegiotrenerandroid.data.services.request.DataHijoRequest
import com.jjmf.colegiotrenerandroid.data.services.request.DataClubRequest
import com.jjmf.colegiotrenerandroid.domain.model.DataHijo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HijosDatosAdminViewModel @Inject constructor(
    private val repository: PersonaRepository
) : ViewModel() {

    var alertAddHijo by mutableStateOf(false)
    var listHijos by mutableStateOf<List<DataHijo>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun getHijos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                when (val res = repository.getDataHijos()) {
                    is Result.Correcto -> listHijos = res.datos ?: emptyList()
                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }


    fun editHijo(data: DataHijoRequest) {
        viewModelScope.launch {
            try {
                isLoading = true
                error = when (val res = repository.editHijo(data)) {
                    is Result.Correcto -> "Guardado"
                    is Result.Error -> res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                getHijos()
            }
        }
    }
}