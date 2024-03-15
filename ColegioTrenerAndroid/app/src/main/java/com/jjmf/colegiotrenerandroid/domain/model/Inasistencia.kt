package com.jjmf.colegiotrenerandroid.domain.model

import java.time.LocalDate

data class Inasistencia(
    val anoaca: String?,
    val mes: Int?,
    val dia: Int?,
    val ctacli: String?,
    val codgra: String?,
    val ctaemp: String?,
    val semana: Int?,
    val leyenda: String?,
    val leyendapp: String?,
    val localDate: LocalDate
)