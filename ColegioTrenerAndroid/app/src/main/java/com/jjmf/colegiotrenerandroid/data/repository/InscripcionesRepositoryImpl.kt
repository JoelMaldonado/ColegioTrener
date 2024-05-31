package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.InscripcionDto
import com.jjmf.colegiotrenerandroid.data.services.InscripcionesService
import com.jjmf.colegiotrenerandroid.data.services.RequestInsertInscripcion
import com.jjmf.colegiotrenerandroid.data.services.ResponseTrener
import com.jjmf.colegiotrenerandroid.domain.model.Inscripcion
import com.jjmf.colegiotrenerandroid.domain.repository.InscripcionesRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import javax.inject.Inject

class InscripcionesRepositoryImpl @Inject constructor(
    private val api: InscripcionesService,
    private val token: TokenUseCase,
    private val prefs: Preferencias
) : InscripcionesRepository {

    override suspend fun getListInscripciones(idHijo: String): Result<List<Inscripcion>> {
        return try {
            val call = api.getListInscripciones(idHijo, token())
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

    override suspend fun save(
        ctacli: String,
        codTipoInscripcion: String,
        codInscripcion: String
    ): Result<String> {
        return try {
            val request = RequestInsertInscripcion(
                ctamae = prefs.getCtamae(),
                ctacli = ctacli,
                codtipoinscripcion = codTipoInscripcion,
                codinscripcion = codInscripcion
            )
            val call = api.insert(
                token = token(),
                request = request
            )
            if (call.isSuccessful) {
                val data = convertJson<ResponseTrener>(call.body()?.incribirAlumnoResult)
                if (data.status == 1) Result.Correcto(data.message)
                else Result.Error(data.message)
            }
            else Result.Error(call.message())
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}
