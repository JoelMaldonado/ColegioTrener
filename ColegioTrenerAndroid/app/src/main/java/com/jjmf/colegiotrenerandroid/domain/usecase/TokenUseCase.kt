package com.jjmf.colegiotrenerandroid.domain.usecase

import android.content.SharedPreferences
import com.auth0.android.jwt.JWT
import com.jjmf.colegiotrenerandroid.data.services.ApiService
import com.jjmf.colegiotrenerandroid.data.services.request.AuthRequest
import javax.inject.Inject


class TokenUseCase @Inject constructor(
    private val api: ApiService,
    private val sharedPreferences: SharedPreferences
) {

    suspend operator fun invoke(): String {
        return try {
            val token = sharedPreferences.getString("TOKEN", null)
            if (token != null) {
                val isExpired = JWT(token).isExpired(0)
                if (isExpired) getTokenApi()
                else token
            } else getTokenApi()
        } catch (e: Exception) {
            throw e
        }
    }

    private fun saveToken(value: String) {
        sharedPreferences.edit().putString("TOKEN", value).apply()
    }

    private suspend fun getTokenApi(): String {
        val request = AuthRequest(
            usuario = "ApiTrener",
            contrasenia = "TRm3k@B#TBaWXH8iWX4eL!"
        )
        val call = api.getToken(request)
        return if (call.isSuccessful && call.body()?.codigo == 1) {
            val newToken = call.body()?.resultado
            if (newToken == null) {
                throw Exception("Error al guardar token")
            } else saveToken(newToken)
            call.body()?.resultado.toString()
        } else {
            throw Exception("Error al obtener el token")
        }
    }
}