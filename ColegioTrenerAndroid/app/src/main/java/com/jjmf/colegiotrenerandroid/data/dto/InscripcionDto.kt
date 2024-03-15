package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Inscripcion

data class InscripcionDto(
    @SerializedName("tipoinscripcion") val tipoinscripcion: String?,
    @SerializedName("codtipoinscripcion") val codtipoinscripcion: String?,
    @SerializedName("inscripcion") val inscripcion: String?,
    @SerializedName("codinscripcion") val codinscripcion: String?,
    @SerializedName("precio") val precio: Double?,
    @SerializedName("estadoinscripcion") val estadoinscripcion: String?
) {
    fun toDomain(): Inscripcion {
        return Inscripcion(
            tipoinscripcion = tipoinscripcion?.trim(),
            codtipoinscripcion = codtipoinscripcion?.trim(),
            inscripcion = inscripcion?.trim(),
            codinscripcion = codinscripcion?.trim(),
            precio = precio ?: 0.0,
            estadoinscripcion = estadoinscripcion?.trim()
        )
    }
}