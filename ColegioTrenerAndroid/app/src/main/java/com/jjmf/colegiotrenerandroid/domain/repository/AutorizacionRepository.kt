package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Autorizacion

interface AutorizacionRepository {
    suspend fun listarAutorizaciones() : Result<List<Autorizacion>>
}