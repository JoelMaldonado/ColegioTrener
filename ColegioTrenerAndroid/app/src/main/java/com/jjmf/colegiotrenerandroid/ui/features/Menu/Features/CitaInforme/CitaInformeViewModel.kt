package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.CitaInforme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.CitaInforme
import com.jjmf.colegiotrenerandroid.domain.repository.CitaInformeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CitaInformeViewModel @Inject constructor(
    private val repository: CitaInformeRepository
) : ViewModel() {

    var fecha = mutableStateOf(LocalDate.now())
    var trimestre = mutableStateOf(Trimestre.Uno)
    var list by mutableStateOf<List<CitaInforme>>(emptyList())
    var error by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    init {
        getTrimestreActual()
        listarCitas()
    }

    private fun getTrimestreActual() {
        viewModelScope.launch {
            try {
                when(val res = repository.getTrimestreActual()){
                    is Result.Correcto -> trimestre.value = Trimestre.entries.find { it.num == res.datos } ?: Trimestre.Uno
                    is Result.Error -> error = res.mensaje
                }
            }catch (e:Exception){
                error = e.message
            }
        }
    }

    fun listarCitas() {
        viewModelScope.launch {
            try {
                isLoading = true
                delay(2000)
                val res = repository.listarCitas(
                    year = fecha.value.year.toString(),
                    trimestre = trimestre.value
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