package com.jjmf.colegiotrenerandroid.domain.model

import java.time.LocalDate

data class EstadoCalPendiente(
    val fechaasignacion: LocalDate?,
    val estado: String?,
    val cantidad: Double
)