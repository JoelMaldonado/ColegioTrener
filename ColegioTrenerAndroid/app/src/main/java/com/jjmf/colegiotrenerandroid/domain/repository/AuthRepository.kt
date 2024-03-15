package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result

interface AuthRepository {

    suspend fun login(usuario: String, clave: String): Result<String>
}