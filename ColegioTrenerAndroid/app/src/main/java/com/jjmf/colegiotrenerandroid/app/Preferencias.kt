package com.jjmf.colegiotrenerandroid.app

import android.content.SharedPreferences
import javax.inject.Inject

class Preferencias @Inject constructor(
    private val sp: SharedPreferences
) {

    private val KEY_RERCUERDAME = "KEY_RERCUERDAME"
    private val KEY_USUARIO = "KEY_USUARIO"
    private val KEY_CLAVE = "KEY_CLAVE"

    fun saveRecuerdame(data: Boolean) {
        sp.edit().putBoolean(KEY_RERCUERDAME, data).apply()
    }

    fun getRecuerdame() = sp.getBoolean(KEY_RERCUERDAME, false)

    fun saveUsuario(data: String) {
        sp.edit().putString(KEY_USUARIO, data).apply()
    }

    fun getUsuario() = sp.getString(KEY_USUARIO, null) ?: throw Exception("Sin ID")

    fun removeUsuario() = sp.edit().remove(KEY_USUARIO)

    fun saveClave(data: String) {
        sp.edit().putString(KEY_CLAVE, data).apply()
    }

    fun getClave() = sp.getString(KEY_CLAVE, null) ?: ""
    fun removeClave() = sp.edit().remove(KEY_CLAVE)

    fun saveLink(link: String?) {
        sp.edit().putString("KEY_LINK", link).apply()
    }

    fun getLink() : String? {
        return sp.getString("KEY_LINK", null)
    }
    fun saveFamilia(data: String?) {
        sp.edit().putString("KEY_FAMILIA", data).apply()
    }

    fun getFamilia() : String? {
        return sp.getString("KEY_FAMILIA", null)
    }
}