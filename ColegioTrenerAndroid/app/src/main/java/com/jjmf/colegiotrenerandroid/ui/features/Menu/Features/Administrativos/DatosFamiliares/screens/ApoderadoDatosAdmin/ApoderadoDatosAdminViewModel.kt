package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.ApoderadoDatosAdmin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.repository.AuthRepository
import com.jjmf.colegiotrenerandroid.domain.repository.PersonaRepository
import com.jjmf.colegiotrenerandroid.domain.model.DataPersona
import com.jjmf.colegiotrenerandroid.util.enums.TipoFamiliar
import com.jjmf.colegiotrenerandroid.util.format
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ApoderadoDatosAdminViewModel @Inject constructor(
    private val repository: PersonaRepository,
    private val auth: AuthRepository
) : ViewModel() {

    var nombre by mutableStateOf("")
    var apodo by mutableStateOf("")
    var tipoDoc by mutableStateOf("")
    var numDoc by mutableStateOf("")
    var fechaNac by mutableStateOf<Date?>(null)
    var distrito by mutableStateOf("")
    var direc by mutableStateOf("")
    var cel by mutableStateOf("")
    var telf by mutableStateOf("")
    var empresa by mutableStateOf("")
    var cargo by mutableStateOf("")
    var telfEmpresa by mutableStateOf("")
    var correo by mutableStateOf("")

    var apoderado by mutableStateOf<DataPersona?>(null)

    var error by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)

    init {
        getDatos()
    }
    private fun getDatos() {
        viewModelScope.launch {
            try {
                isLoading = true
                when (val res = repository.datos()) {
                    is Result.Correcto -> {
                        apoderado = res.datos?.find { it.tipo == TipoFamiliar.Apoderado}
                        setearDatos(apoderado)
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


    fun save() {
        viewModelScope.launch {
            try {
                val res = repository.updateApoderado(
                    tipo = "APODERADO",
                    fechanacimiento = "${fechaNac?.format("yyyy-MM-dd") ?: "1999-01-01"}T00:00:00",
                    distrito = distrito,
                    direccion = direc,
                    celular = cel,
                    telefono = telf,
                    empresa = empresa,
                    telefempresa = telfEmpresa,
                    cargo = cargo,
                    e_mailp = correo
                )
                when (res) {
                    is Result.Correcto -> {
                        isSuccess = true
                        getDatos()
                    }
                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

    private fun setearDatos(persona: DataPersona?) {
        nombre = persona?.nombre ?: ""
        apodo = persona?.alias ?: ""
        tipoDoc = persona?.tipodoc ?: ""
        numDoc = persona?.documento ?: ""
        fechaNac = persona?.fechanacimiento
        distrito = persona?.distrito ?: ""
        direc = persona?.distrito ?: ""
        cel = persona?.celular ?: ""
        telf = persona?.telefono ?: ""
        empresa = persona?.empresa ?: ""
        cargo = persona?.cargo ?: ""
        telfEmpresa = persona?.telefempresa ?: ""
        correo = persona?.e_mailp ?: ""
    }
}