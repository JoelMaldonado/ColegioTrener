package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Autorizacion
import com.jjmf.colegiotrenerandroid.domain.model.EstadoAutorizacion
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.Estado

interface AutorizacionRepository {
    suspend fun listarAutorizaciones(
        estado: Estado
    ): Result<List<Autorizacion>>

    suspend fun estado(idPermiso: String): Result<List<EstadoAutorizacion>>

    suspend fun grabar(
        idPermiso: String,
        ctacli: String,
        autorizo: String
    ): Result<Boolean>
}