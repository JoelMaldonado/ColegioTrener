package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.PadresDatosAdmin

import android.content.Context
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.repository.AuthRepository
import com.jjmf.colegiotrenerandroid.domain.repository.PersonaRepository
import com.jjmf.colegiotrenerandroid.domain.model.DataPersona
import com.jjmf.colegiotrenerandroid.domain.model.Distrito
import com.jjmf.colegiotrenerandroid.domain.repository.ComboRepository
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.util.enums.TipoFamiliar
import com.jjmf.colegiotrenerandroid.util.format
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PadresDatosAdminViewModel @Inject constructor(
    private val repository: PersonaRepository,
    private val repoCombo: ComboRepository
) : ViewModel() {

    var tab by mutableStateOf(TipoFamiliar.Padre)

    var nombre by mutableStateOf("")
    var apodo by mutableStateOf("")
    var tipoDoc by mutableStateOf("")
    var numDoc by mutableStateOf("")
    val fecha = mutableStateOf(TextFieldValue())
    var distrito = mutableStateOf<Distrito?>(null)
    var direc by mutableStateOf("")
    var cel by mutableStateOf("")
    var telf by mutableStateOf("")
    var empresa by mutableStateOf("")
    var cargo by mutableStateOf("")
    var telfEmpresa by mutableStateOf("")
    var correo by mutableStateOf("")
    var isCorreoEnabled by mutableStateOf(true)

    var listDistritos by mutableStateOf<List<Distrito>>(emptyList())

    var padre by mutableStateOf<DataPersona?>(null)
    var madre by mutableStateOf<DataPersona?>(null)
    var error by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    init {
        getDistritos()
    }

    private fun getDistritos() {
        viewModelScope.launch {
            try {
                when (val res = repoCombo.getDistritos()) {
                    is Result.Correcto -> {
                        listDistritos = res.datos ?: emptyList()
                        getDatos()
                    }

                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

    private fun getDatos() {
        viewModelScope.launch {
            try {
                isLoading = true
                when (val res = repository.datos()) {
                    is Result.Correcto -> {
                        padre = res.datos?.find { it.tipo == TipoFamiliar.Padre }
                        madre = res.datos?.find { it.tipo == TipoFamiliar.Madre }
                        when (tab) {
                            TipoFamiliar.Padre -> setearDatos(padre)
                            TipoFamiliar.Madre -> setearDatos(madre)
                            TipoFamiliar.Apoderado -> {}
                            TipoFamiliar.Hijo -> {}
                            TipoFamiliar.Club -> {}
                        }
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

    fun setearDatos(persona: DataPersona?) {
        nombre = persona?.nombre ?: ""
        apodo = persona?.alias ?: ""
        tipoDoc = persona?.tipodoc ?: ""
        numDoc = persona?.documento ?: ""
        fecha.value = TextFieldValue(text = persona?.fechanacimiento?.format("dd/MM/yyyy") ?: "01/01/1999")
        distrito.value = listDistritos.find { it.coddis == persona?.distrito }
        direc = persona?.direccion ?: ""
        cel = persona?.celular ?: ""
        telf = persona?.telefono ?: ""
        empresa = persona?.empresa ?: ""
        cargo = persona?.cargo ?: ""
        telfEmpresa = persona?.telefempresa ?: ""
        correo = persona?.e_mailp ?: ""
        isCorreoEnabled = persona?.emailbloqueo == true
    }

    fun save(context: Context) {
        viewModelScope.launch {
            try {
                val res = repository.updateApoderado(
                    tipo = tab.name.uppercase(),
                    fechanacimiento = fecha.value.text,
                    distrito = distrito.value?.coddis,
                    direccion = direc,
                    celular = cel,
                    telefono = telf,
                    empresa = empresa,
                    telefEmpresa = telfEmpresa,
                    cargo = cargo,
                    email = correo
                )
                when (res) {
                    is Result.Correcto -> {
                        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).apply {
                            titleText = res.datos
                            confirmButtonBackgroundColor = ColorP1.hashCode()
                            setConfirmButton("Continuar") {
                                getDatos()
                                dismissWithAnimation()
                            }
                            setCancelable(false)
                            show()
                        }
                    }

                    is Result.Error -> error = res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

}