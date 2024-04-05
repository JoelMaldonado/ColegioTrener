package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.repository.AuthRepository
import com.jjmf.colegiotrenerandroid.domain.repository.PagosRepository
import com.jjmf.colegiotrenerandroid.domain.model.Pago
import com.jjmf.colegiotrenerandroid.util.format
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PagosViewModel @Inject constructor(
    private val repository: PagosRepository,
    private val auth: AuthRepository
) : ViewModel() {

    var ctacli by mutableStateOf<String?>(null)
    var year by mutableStateOf(LocalDate.now())

    var listPagosPendientes by mutableStateOf<List<Pago>>(emptyList())
    var isLoadingPendientes by mutableStateOf(false)

    var listPagosVencidos by mutableStateOf<List<Pago>>(emptyList())
    var isLoadingVencidos by mutableStateOf(false)

    var listPagosRealizados by mutableStateOf<List<Pago>>(emptyList())
    var isLoadingRealizados by mutableStateOf(false)

    var error by mutableStateOf<String?>(null)


    fun getPagosPendientes(ctacli: String) {
        viewModelScope.launch {
            try {
                isLoadingPendientes = true
                when (val res = repository.getPagosPendientes(ctacli)) {
                    is Result.Correcto -> {
                        listPagosPendientes = res.datos ?: emptyList()
                    }

                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoadingPendientes = false
            }
        }
    }

    fun getPagosVencidos(ctacli: String) {
        viewModelScope.launch {
            try {
                isLoadingVencidos = true
                when (val res = repository.getPagosVencidos(ctacli)) {
                    is Result.Correcto -> {
                        listPagosVencidos = res.datos ?: emptyList()
                    }

                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoadingVencidos = false
            }
        }
    }

    fun getPagosRealizados(ctacli: String) {
        viewModelScope.launch {
            try {
                isLoadingRealizados = true
                when (val res = repository.getPagosRealizados(ctacli = ctacli, year = year.format("yyyy"))) {
                    is Result.Correcto -> {
                        listPagosRealizados = res.datos ?: emptyList()
                    }

                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoadingRealizados = false
            }
        }
    }

    fun yearBefore() {
        year = year.minusYears(1)
        getPagosRealizados(ctacli.toString())
    }

    fun yearAfter() {
        year = year.plusYears(1)
        getPagosRealizados(ctacli.toString())
    }

}