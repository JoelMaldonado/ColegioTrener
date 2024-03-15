package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.PagoDto
import com.jjmf.colegiotrenerandroid.data.services.PagoService
import com.jjmf.colegiotrenerandroid.domain.model.Pago
import com.jjmf.colegiotrenerandroid.domain.repository.PagosRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import com.jjmf.colegiotrenerandroid.util.format
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

class PagosRepositoryImpl @Inject constructor(
    private val api: PagoService,
    private val token: TokenUseCase
) : PagosRepository {

    override suspend fun getPagosPendientes(ctacli: String): Result<List<Pago>> {
        return try {
            val call = api.getPagosPendientes(
                ctacli = ctacli,
                year = Date().format("yyyy"),
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<PagoDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun getPagosVencidos(ctacli: String): Result<List<Pago>> {
        return try {
            val call = api.getPagosVencidos(
                ctacli = ctacli,
                year = Date().format("yyyy"),
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<PagoDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun getPagosRealizados(ctacli: String, year:String): Result<List<Pago>> {
        return try {
            val call = api.getPagosRealizados(
                ctacli = ctacli,
                year = year,
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<PagoDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}