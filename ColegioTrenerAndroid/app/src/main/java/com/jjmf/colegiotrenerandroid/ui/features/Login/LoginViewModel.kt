package com.jjmf.colegiotrenerandroid.ui.features.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val prefs: Preferencias
) : ViewModel() {

    var usuario by mutableStateOf("")
    var clave by mutableStateOf("")
    var recuerdame by mutableStateOf(false)
    var toMenu by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    var session by mutableStateOf(false)

    init {
        if (prefs.getRecuerdame()){
            usuario = prefs.getUsuario()
            verifySession()
        } else {
            usuario = ""
            clave = ""
        }
        recuerdame = prefs.getRecuerdame()
    }

    fun login() {
        viewModelScope.launch {
            try {
                isLoading = true
                when (val resLogin = repository.login(usuario, clave)) {
                    is Result.Correcto -> {
                        toMenu = true
                        prefs.saveRecuerdame(recuerdame)
                        prefs.saveCtamae(usuario)
                        if (recuerdame){
                            prefs.saveUsuario(usuario)
                        } else {
                            prefs.removeUsuario()
                        }
                    }
                    is Result.Error -> error = resLogin.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }

        }
    }

    fun verifySession() {
        if (prefs.getCtamae().isNotEmpty()){
            session = true
        } else {
            session = false
        }
    }

}