package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Incumplimiento

interface TareaRepository {
    suspend fun listarIncumplimientos(ctactli:String): Result<List<Incumplimiento>>

}

