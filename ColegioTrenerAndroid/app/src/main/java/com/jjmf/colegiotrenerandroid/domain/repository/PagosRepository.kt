package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Pago

interface PagosRepository {

    suspend fun getPagosPendientes(ctacli:String): Result<List<Pago>>

    suspend fun getPagosVencidos(ctacli:String): Result<List<Pago>>

    suspend fun getPagosRealizados(ctacli:String, year:String): Result<List<Pago>>

}