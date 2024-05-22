package com.jjmf.colegiotrenerandroid.app

import android.content.SharedPreferences
import javax.inject.Inject

class Preferencias @Inject constructor(
    private val sp: SharedPreferences
) {

    private val KEY_RERCUERDAME = "KEY_RERCUERDAME"
    private val KEY_CTAMAE = "KEY_CTAMAE"
    private val KEY_USUARIO = "KEY_USUARIO"

    fun saveRecuerdame(data: Boolean) {
        sp.edit().putBoolean(KEY_RERCUERDAME, data).apply()
    }

    fun getRecuerdame() = sp.getBoolean(KEY_RERCUERDAME, false)

    fun saveUsuario(data: String) {
        sp.edit().putString(KEY_USUARIO, data).apply()
    }

    fun getUsuario() = sp.getString(KEY_USUARIO, null) ?: throw Exception("Sin ID")

    fun removeUsuario() = sp.edit().remove(KEY_USUARIO).apply()
    fun saveCtamae(data: String) {
        sp.edit().putString(KEY_CTAMAE, data).apply()
    }

    fun getCtamae() = sp.getString(KEY_CTAMAE, null) ?: throw Exception("Sin ID")

    fun removeCtamae() = sp.edit().remove(KEY_CTAMAE).apply()

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