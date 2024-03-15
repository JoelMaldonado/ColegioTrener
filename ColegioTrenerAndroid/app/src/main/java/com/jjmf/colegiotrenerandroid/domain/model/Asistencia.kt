package com.jjmf.colegiotrenerandroid.domain.model

data class Asistencia(
    val fecha: String?,
    val trimestre: String?,
    val asistio: Double,
    val tardanza: Double,
    val justificada: Double,
    val injustificada: Double
)