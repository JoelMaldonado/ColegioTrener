package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.AsistenciaDto
import com.jjmf.colegiotrenerandroid.data.dto.InasistenciaDto
import com.jjmf.colegiotrenerandroid.data.services.AsistenciaService
import com.jjmf.colegiotrenerandroid.domain.model.Asistencia
import com.jjmf.colegiotrenerandroid.domain.model.Inasistencia
import com.jjmf.colegiotrenerandroid.domain.repository.AsistenciaRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import javax.inject.Inject

class AsistenciaRepositoryImpl @Inject constructor(
    private val api: AsistenciaService,
    private val token: TokenUseCase
) : AsistenciaRepository {
    override suspend fun totalMes(year: String, month: String): Result<Asistencia> {
        return try {
            val call = api.listarAsistenciasPorMes(
                year = year,
                month = month,
                ctacli = "00002528",
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<AsistenciaDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body.first())
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun listarInasistenciasPorAlumno(
        year: String,
        month: String
    ): Result<List<Inasistencia>> {
        return try {
            val call = api.listarInasistenciasPorAlumno(
                year = year,
                month = month,
                ctacli = "00002528",
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<InasistenciaDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }

    }

}