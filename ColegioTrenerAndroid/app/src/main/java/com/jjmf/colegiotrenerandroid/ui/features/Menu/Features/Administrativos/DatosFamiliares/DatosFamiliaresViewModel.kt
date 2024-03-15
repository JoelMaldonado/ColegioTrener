package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jjmf.colegiotrenerandroid.util.enums.TipoFamiliar
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DatosFamiliaresViewModel @Inject constructor(

) : ViewModel() {

    var tab by mutableStateOf(TipoFamiliar.Padre)
}