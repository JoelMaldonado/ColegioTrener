package com.jjmf.colegiotrenerandroid.domain.model

import java.util.Date

data class Pago(
    val anoaca: String?,
    val codconcepto: String?,
    val concepto: String?,
    val fecven: Date,
    val saldo: Double,
    val estado: String?,
    val tipdoc: String?,
    val numdoc: String?,
    val mediopago: String?,
    val fecpag: Date,
    val importepagado: Double,
    val mora: Double,
)