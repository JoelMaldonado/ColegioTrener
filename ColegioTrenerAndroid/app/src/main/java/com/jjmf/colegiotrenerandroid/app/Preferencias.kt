package com.jjmf.colegiotrenerandroid.app

import android.content.SharedPreferences
import javax.inject.Inject

class Preferencias @Inject constructor(
    private val sp: SharedPreferences
) {

    private val KEY_RERCUERDAME = "KEY_RERCUERDAME"
    private val KEY_USUARIO = "KEY_USUARIO"

    fun saveRecuerdame(data: String) {
        sp.edit().putString(KEY_RERCUERDAME, data).apply()
    }

    fun getRecuerdame() = sp.getString(KEY_RERCUERDAME, null)

    fun saveUsuario(data: String) {
        sp.edit().putString(KEY_USUARIO, data).apply()
    }

    fun getUsuario(): String {
        return sp.getString(KEY_USUARIO, null) ?: throw Exception("No se pudo obtener el usuario")
    }
}