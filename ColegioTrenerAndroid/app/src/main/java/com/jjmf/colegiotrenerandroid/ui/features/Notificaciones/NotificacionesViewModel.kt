package com.jjmf.colegiotrenerandroid.ui.features.Notificaciones

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Notificacion
import com.jjmf.colegiotrenerandroid.domain.repository.AuthRepository
import com.jjmf.colegiotrenerandroid.domain.repository.NotifacionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificacionesViewModel @Inject constructor(
    private val repository: NotifacionRepository
) : ViewModel() {

    var list by mutableStateOf<List<Notificacion>>(emptyList())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun getNotificaciones(ctacli: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                delay(500)
                when(val res = repository.getAll(ctacli)){
                    is Result.Correcto -> list = res.datos ?: emptyList()
                    is Result.Error -> error = res.mensaje
                }
            }catch (e:Exception){
                error = e.message
            }finally {
                isLoading = false
            }
        }
    }

}