package com.jjmf.colegiotrenerandroid.ui.features.Menu

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jjmf.colegiotrenerandroid.app.Preferencias
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val prefs: Preferencias
) : ViewModel() {

    var title by mutableStateOf("")
    var familia by mutableStateOf(prefs.getFamilia())


}