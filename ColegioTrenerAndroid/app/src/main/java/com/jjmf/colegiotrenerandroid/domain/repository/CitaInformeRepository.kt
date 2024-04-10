package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.CitaInforme
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.CitaInforme.Trimestre

interface CitaInformeRepository {
    suspend fun listarCitas(year: String, trimestre: Trimestre): Result<List<CitaInforme>>

    suspend fun getTrimestreActual(): Result<String>
}