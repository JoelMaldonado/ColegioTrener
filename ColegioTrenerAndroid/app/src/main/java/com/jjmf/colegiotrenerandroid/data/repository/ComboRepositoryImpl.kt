package com.jjmf.colegiotrenerandroid.data.repository

import com.google.gson.Gson
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.ClubDto
import com.jjmf.colegiotrenerandroid.data.dto.DistritoDto
import com.jjmf.colegiotrenerandroid.data.services.ApiService
import com.jjmf.colegiotrenerandroid.domain.model.Club
import com.jjmf.colegiotrenerandroid.domain.model.Distrito
import com.jjmf.colegiotrenerandroid.domain.repository.ComboRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import javax.inject.Inject

class ComboRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val token: TokenUseCase
) : ComboRepository {
    override suspend fun getClubes(): Result<List<Club>> {
        return try {
            val call = api.getClubs(token())
            if (call.isSuccessful) {
                val list = Gson().fromJson(call.body(), Array<ClubDto>::class.java)
                Result.Correcto(list.map { it.toDomain() })
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun getDistritos(): Result<List<Distrito>> {
        return try {
            val call = api.getDistrito(token())
            if (call.isSuccessful) {
                val list = Gson().fromJson(call.body(), Array<DistritoDto>::class.java)
                Result.Correcto(list.map { it.toDomain() })
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}