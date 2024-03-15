package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Asistencia
import com.jjmf.colegiotrenerandroid.domain.model.Inasistencia

interface AsistenciaRepository {
    suspend fun totalMes(year:String, month:String) : Result<Asistencia>
    suspend fun listarInasistenciasPorAlumno(year:String, month:String): Result<List<Inasistencia>>
}