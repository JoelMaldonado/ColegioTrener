package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.AutorizacionDto
import com.jjmf.colegiotrenerandroid.data.services.AutorizacionService
import com.jjmf.colegiotrenerandroid.domain.model.Autorizacion
import com.jjmf.colegiotrenerandroid.domain.repository.AutorizacionRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import javax.inject.Inject

class AutorizacionRepositoryImpl @Inject constructor(
    private val api: AutorizacionService,
    private val token: TokenUseCase
) : AutorizacionRepository {
    override suspend fun listarAutorizaciones(): Result<List<Autorizacion>> {
        return try {
            val call = api.listarAutorizaciones(
                ctamae = "00002070",
                estado = "A",
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<AutorizacionDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}