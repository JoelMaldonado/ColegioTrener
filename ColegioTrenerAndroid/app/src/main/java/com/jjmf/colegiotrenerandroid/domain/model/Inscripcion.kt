package com.jjmf.colegiotrenerandroid.domain.model

data class Inscripcion(
    val tipoinscripcion: String?,
    val codtipoinscripcion: String?,
    val inscripcion: String?,
    val codinscripcion: String?,
    val precio: Double,
    val estadoinscripcion: Boolean,
    val inscripcionbloqueo: String?
)