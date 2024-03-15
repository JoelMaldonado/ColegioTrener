package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.CitaInforme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.CitaInforme
import com.jjmf.colegiotrenerandroid.domain.repository.CitaInformeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CitaInformeViewModel @Inject constructor(
    private val repository: CitaInformeRepository
) : ViewModel() {

    var year by mutableStateOf(LocalDate.now())
    var trimestre by mutableStateOf(Trimestre.Uno)
    var list by mutableStateOf<List<CitaInforme>>(emptyList())
    var error by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    init {
        listarCitas()
    }

    fun listarCitas() {
        viewModelScope.launch {
            try {
                val res = repository.listarCitas(
                    year = year.year.toString(),
                    trimestre = trimestre
                )
                when (res) {
                    is Result.Correcto -> list = res.datos ?: emptyList()
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

enum class Trimestre(val nombre: String, val num: String) {
    Uno("I Trimestre", "0"),
    Dos("II Trimestre", "1"),
    Tres("III Trimestre", "2")
}