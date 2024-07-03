package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Notificacion

interface NotifacionRepository {
    suspend fun getAll(ctacli: String) : Result<List<Notificacion>>
}