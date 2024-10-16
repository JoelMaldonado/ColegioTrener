package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.AutorizacionDto
import com.jjmf.colegiotrenerandroid.data.dto.CarnetDto
import com.jjmf.colegiotrenerandroid.data.services.AsistenciaService
import com.jjmf.colegiotrenerandroid.data.services.AutorizacionService
import com.jjmf.colegiotrenerandroid.data.services.ResponseTrener
import com.jjmf.colegiotrenerandroid.domain.model.Carnet
import com.jjmf.colegiotrenerandroid.domain.repository.CarnetRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import com.jjmf.colegiotrenerandroid.util.convertJson2
import javax.inject.Inject

class CarnetRepositoryImp @Inject constructor(
    private val api: AsistenciaService,
    private val token: TokenUseCase,
    private val prefs: Preferencias
): CarnetRepository{
    override suspend fun getCarnet(ctaCli:String) : Result<Carnet> {
        return try {
            val call = api.obtenerCarnet(
                ctacli = ctaCli,
                token = token()
            )
            if (call.isSuccessful) {
                val data = convertJson<CarnetDto>(call.body())
                Result.Correcto(data.toDomain())
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }


}