package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.InscripcionDto
import com.jjmf.colegiotrenerandroid.data.services.ApiService
import com.jjmf.colegiotrenerandroid.data.services.InscripcionesService
import com.jjmf.colegiotrenerandroid.data.services.request.AuthRequest
import com.jjmf.colegiotrenerandroid.domain.model.Inscripcion
import com.jjmf.colegiotrenerandroid.domain.repository.InscripcionesRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class InscripcionesRepositoryImpl @Inject constructor(
    private val api: InscripcionesService,
    private val tokenUseCase: TokenUseCase
) : InscripcionesRepository {

    var token = runBlocking { tokenUseCase() }

    override suspend fun getListInscripciones(idHijo:String): Result<List<Inscripcion>> {
        return try {
            val call = api.getListInscripciones(idHijo,token)
            if (call.isSuccessful) {
                val body = convertJson<Array<InscripcionDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}
