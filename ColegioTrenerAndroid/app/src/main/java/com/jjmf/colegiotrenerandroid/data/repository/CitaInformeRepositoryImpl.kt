package com.jjmf.colegiotrenerandroid.data.repository

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.CitaInformeDto
import com.jjmf.colegiotrenerandroid.data.services.CitaInformeService
import com.jjmf.colegiotrenerandroid.domain.model.CitaInforme
import com.jjmf.colegiotrenerandroid.domain.repository.CitaInformeRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.CitaInforme.Trimestre
import com.jjmf.colegiotrenerandroid.util.convertJson
import javax.inject.Inject

class CitaInformeRepositoryImpl @Inject constructor(
    private val api: CitaInformeService,
    private val token: TokenUseCase,
    private val prefs: Preferencias
) : CitaInformeRepository {
    override suspend fun listarCitas(
        year: String,
        trimestre: Trimestre
    ): Result<List<CitaInforme>> {
        return try {
            val call = api.listarCitasInforme(
                ctamae = prefs.getCtamae(),
                year = year,
                trimestre = trimestre.num,
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<CitaInformeDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }

    }

    override suspend fun getTrimestreActual(): Result<Trimestre> {
        return try {
            val call = api.getTrimestreActual(token = token())
            if (call.isSuccessful) {
                val body = convertJson<Array<TrimestreDto>>(call.body()).firstOrNull()?.trimestre
                val trim: Trimestre = when(body){
                    "1" -> Trimestre.Uno
                    "2" -> Trimestre.Dos
                    "3" -> Trimestre.Tres
                    else -> Trimestre.Uno
                }
                Result.Correcto(trim)
            }
            else Result.Error(call.message())
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}

data class TrimestreDto(
    @SerializedName("trimestre") val trimestre:String?
)