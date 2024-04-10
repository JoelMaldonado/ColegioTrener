package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.IncumplimientoDto
import com.jjmf.colegiotrenerandroid.data.services.TareaService
import com.jjmf.colegiotrenerandroid.domain.model.Incumplimiento
import com.jjmf.colegiotrenerandroid.domain.repository.TareaRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import javax.inject.Inject

class TareaRepositoryImpl @Inject constructor(
    private val api: TareaService,
    private val token: TokenUseCase
) : TareaRepository {
    override suspend fun listarIncumplimientos(ctacli:String): Result<List<Incumplimiento>> {
        return try {
            val call = api.getListaIncumplimientos(
                ctacli = ctacli,
                token = token()
            )
            if (call.isSuccessful){
                val data = convertJson<Array<IncumplimientoDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(data)
            } else Result.Error(call.message())
        } catch (e:Exception){
            Result.Error(e.message)
        }
    }
}