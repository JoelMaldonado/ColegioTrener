package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.services.request.DataClubRequest
import com.jjmf.colegiotrenerandroid.data.services.request.DataHijoRequest
import com.jjmf.colegiotrenerandroid.domain.model.DataClub
import com.jjmf.colegiotrenerandroid.domain.model.DataHijo
import com.jjmf.colegiotrenerandroid.domain.model.DataPersona
import com.jjmf.colegiotrenerandroid.domain.model.Hijo

interface PersonaRepository {

    suspend fun datos(): Result<List<DataPersona>>

    suspend fun getDataHijos(): Result<List<DataHijo>>

    suspend fun getHijos(): Result<List<Hijo>>

    suspend fun getClubes(): Result<List<DataClub>>

    suspend fun editHijo(data: DataHijoRequest): Result<String>

    suspend fun editClub(data: DataClubRequest): Result<String>

    suspend fun updateApoderado(
        tipo: String,
        fechanacimiento: String,
        distrito: String?,
        direccion: String,
        celular: String,
        telefono: String,
        empresa: String,
        telefEmpresa: String,
        cargo: String,
        email: String
    ): Result<String>
}