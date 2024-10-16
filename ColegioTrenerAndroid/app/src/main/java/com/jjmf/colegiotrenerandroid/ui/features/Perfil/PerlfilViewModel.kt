package com.jjmf.colegiotrenerandroid.ui.features.Perfil

import androidx.lifecycle.ViewModel
import com.jjmf.colegiotrenerandroid.app.Preferencias
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PerlfilViewModel @Inject constructor(
    private val prefs: Preferencias
) : ViewModel() {

    fun logOut(){
        prefs.saveRecuerdame(false)
    }

}