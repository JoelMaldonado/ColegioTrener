package com.jjmf.colegiotrenerandroid.ui.components.SelectHijo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Hijo
import com.jjmf.colegiotrenerandroid.domain.repository.PersonaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SelectHijoViewModel @Inject constructor(
    private val repository: PersonaRepository
) : ViewModel() {

    var hijoSelected by mutableStateOf<Hijo?>(null)
    var listHijos by mutableStateOf<List<Hijo>>(emptyList())
    var error by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)
    var first: () -> Unit = {}

    init {
        getListHijos()
    }

    private fun getListHijos() {
        viewModelScope.launch {
            try {
                isLoading = true
                when (val res = repository.getHijos()) {
                    is Result.Correcto -> {
                        listHijos = res.datos ?: emptyList()
                        hijoSelected = res.datos?.first()
                        first()
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