package com.jjmf.colegiotrenerandroid.domain.model

import java.util.Date


data class Notificacion(
    val idrecordatorio: Int?,
    val anoaca: String?,
    val titulo: String?,
    val descripcion: String?,
    val vinculo: String?,
    val usupro: String?,
    val fecpro: Date
)