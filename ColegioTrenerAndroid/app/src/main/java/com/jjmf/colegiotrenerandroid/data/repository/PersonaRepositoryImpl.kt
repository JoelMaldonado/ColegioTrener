package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.DataClubDto
import com.jjmf.colegiotrenerandroid.data.dto.DataHijoDto
import com.jjmf.colegiotrenerandroid.data.dto.DataPersonaDto
import com.jjmf.colegiotrenerandroid.data.dto.HijoDto
import com.jjmf.colegiotrenerandroid.data.services.ApiService
import com.jjmf.colegiotrenerandroid.data.services.request.AddClubRequest
import com.jjmf.colegiotrenerandroid.data.services.request.AddHijoRequest
import com.jjmf.colegiotrenerandroid.domain.model.DataClub
import com.jjmf.colegiotrenerandroid.domain.model.DataHijo
import com.jjmf.colegiotrenerandroid.domain.model.DataPersona
import com.jjmf.colegiotrenerandroid.domain.model.Hijo
import com.jjmf.colegiotrenerandroid.domain.repository.PersonaRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import javax.inject.Inject

class PersonaRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val token: TokenUseCase,
    private val prefs: Preferencias
) : PersonaRepository {

    override suspend fun datos(): Result<List<DataPersona>> {
        return try {
            val call = api.data(token = token(), ctli = prefs.getUsuario())
            if (call.isSuccessful) {
                val list = convertJson<Array<DataPersonaDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(list)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun getDataHijos(): Result<List<DataHijo>> {
        return try {
            val call = api.getDataHijos(token = token(), ctli = prefs.getUsuario())
            if (call.isSuccessful) {
                val list = convertJson<Array<DataHijoDto>>(call.body())
                return Result.Correcto(list.map { it.toDomain() })
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun getHijos(): Result<List<Hijo>> {
        return try {
            val call = api.getHijosMatriculados(token = token(), ctli = "00002070")
            if (call.isSuccessful) {
                val list = convertJson<Array<HijoDto>>(call.body())
                return Result.Correcto(list.map { it.toDomain() })
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun getClubes(): Result<List<DataClub>> {
        return try {
            val call = api.getDataClubes(token(), ctli = prefs.getUsuario())
            if (call.isSuccessful) {
                val list = convertJson<Array<DataClubDto>>(call.body())
                return Result.Correcto(list.map { it.toDomain() })
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun addHijo(data: AddHijoRequest): Result<String> {
        return try {
            val call = api.insertHijo(data, token())
            if (call.isSuccessful) {
                Result.Correcto("Se inserto")
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun addClub(data: AddClubRequest): Result<String> {
        return try {
            val call = api.insertClub(data, token())
            if (call.isSuccessful) {
                Result.Correcto("Insertado")
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}