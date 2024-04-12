package com.jjmf.colegiotrenerandroid.domain.model

import java.util.Date

data class EstadoCalPendienteDia(
    val fecpro:Date?,
    val curso:String?,
    val tarea:String?,
    val estado:String?,
    val fechaasignacion:Date?,
    val fechaentrega:Date?
)
