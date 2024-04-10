package com.jjmf.colegiotrenerandroid.ui.features.Inicio

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.jjmf.colegiotrenerandroid.app.Preferencias
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InicioViewModel @Inject constructor(
    private val prefs: Preferencias
) : ViewModel() {

    var link = prefs.getLink()

}