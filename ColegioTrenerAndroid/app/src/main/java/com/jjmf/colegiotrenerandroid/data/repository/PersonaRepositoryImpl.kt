package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.DataClubDto
import com.jjmf.colegiotrenerandroid.data.dto.DataHijoDto
import com.jjmf.colegiotrenerandroid.data.dto.DataPersonaDto
import com.jjmf.colegiotrenerandroid.data.dto.HijoDto
import com.jjmf.colegiotrenerandroid.data.services.ApiService
import com.jjmf.colegiotrenerandroid.data.services.RequestUpdateApoderado
import com.jjmf.colegiotrenerandroid.data.services.ResponseTrener
import com.jjmf.colegiotrenerandroid.data.services.request.DataClubRequest
import com.jjmf.colegiotrenerandroid.data.services.request.DataHijoRequest
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
            val call = api.data(token = token(), ctli = prefs.getCtamae())
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
            val call = api.getDataHijos(token = token(), ctmae = prefs.getCtamae())
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
            val ctamae = prefs.getCtamae()
            val call = api.getHijosMatriculados(token = token(), ctamae = ctamae)
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
            val call = api.getDataClubes(token(), ctli = prefs.getCtamae())
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

    override suspend fun editHijo(data: DataHijoRequest): Result<String> {
        return try {
            val call = api.insertHijo(data.copy(ctamae = prefs.getCtamae()), token())
            if (call.isSuccessful) {
                Result.Correcto("Guardado")
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun editClub(data: DataClubRequest): Result<String> {
        return try {
            val call = api.insertClub(data.copy(ctamae = prefs.getCtamae()), token())
            if (call.isSuccessful) {
                Result.Correcto("Guardado")
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun updateApoderado(
        tipo: String,
        fechanacimiento: String,
        distrito: String?,
        direccion: String,
        celular: String,
        telefono: String,
        empresa: String,
        telefEmpresa: String,
        cargo: String,
        email: String,
    ): Result<Nothing> {
        return try {
            val request = RequestUpdateApoderado(
                ctamae = prefs.getCtamae(),
                tipo = tipo,
                fechanacimiento = fechanacimiento,
                distrito = distrito,
                direccion = direccion,
                celular = celular,
                telefono = telefono,
                empresa = empresa,
                telefEmpresa = telefEmpresa,
                cargo = cargo,
                email = email
            )
            val call = api.updateApoderado(token = token(), request = request)
            if (call.isSuccessful) {
                val data = convertJson<ResponseTrener>(call.body())
                if (data.status == 1) Result.Correcto(null)
                else Result.Error(data.message)
            } else Result.Error(call.message())
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }
}