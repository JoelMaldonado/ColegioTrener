package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.services.request.AddClubRequest
import com.jjmf.colegiotrenerandroid.data.services.request.AddHijoRequest
import com.jjmf.colegiotrenerandroid.domain.model.DataClub
import com.jjmf.colegiotrenerandroid.domain.model.DataHijo
import com.jjmf.colegiotrenerandroid.domain.model.DataPersona
import com.jjmf.colegiotrenerandroid.domain.model.Hijo

interface PersonaRepository {

    suspend fun datos(): Result<List<DataPersona>>

    suspend fun getDataHijos(): Result<List<DataHijo>>

    suspend fun getHijos(): Result<List<Hijo>>

    suspend fun getClubes(): Result<List<DataClub>>

    suspend fun addHijo(data: AddHijoRequest): Result<String>

    suspend fun addClub(data: AddClubRequest): Result<String>
}