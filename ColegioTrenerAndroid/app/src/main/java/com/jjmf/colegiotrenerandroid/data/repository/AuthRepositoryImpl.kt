package com.jjmf.colegiotrenerandroid.data.repository

import android.util.Log
import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.services.ApiService
import com.jjmf.colegiotrenerandroid.data.services.request.AuthRequest
import com.jjmf.colegiotrenerandroid.domain.repository.AuthRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val token: TokenUseCase,
    private val prefs: Preferencias
) : AuthRepository {

    override suspend fun login(usuario: String, clave: String): Result<String> {
        return try {
            val request = AuthRequest(
                usuario = usuario,
                contrasenia = clave
            )
            val call = api.login(request, token())
            if (call.isSuccessful) {
                val body = call.body()
                if (body?.codigo == 1) {
                    prefs.saveUsuario(usuario)
                    prefs.saveLink(body.linkLoginIntranet)
                    prefs.saveFamilia(body.familia)
                    Result.Correcto("Login Exitoso")
                } else {
                    Result.Error(body?.resultado ?: "Error al inciar sesión")
                }
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}

