package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Carnet
import com.jjmf.colegiotrenerandroid.domain.model.Inscripcion

interface CarnetRepository {

    suspend fun getCarnet(ctaCli:String): Result<Carnet>
}