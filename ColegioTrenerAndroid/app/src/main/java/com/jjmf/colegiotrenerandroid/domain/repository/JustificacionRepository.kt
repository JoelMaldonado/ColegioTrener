package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Justificacion
import com.jjmf.colegiotrenerandroid.domain.model.Razones

interface JustificacionRepository {

    suspend fun getJustificaciones(ctaCli:String, year: String): Result<List<Justificacion>>
    suspend fun getRazones(): Result<List<Razones>>
    suspend fun saveJustificacion(
        fecha:String,
        ctaCli:String,
        idRazon:String,
        comentario:String,
        fileName:String
    ): Result<String>
}