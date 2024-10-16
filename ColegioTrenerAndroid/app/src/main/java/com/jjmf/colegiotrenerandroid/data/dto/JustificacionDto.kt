package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Justificacion

data class JustificacionDto(
    @SerializedName("fecha") val fecha:String?,
    @SerializedName("estado") val estado:String?,
    @SerializedName("accion") val accion:String?
) {
    fun toDomain(): Justificacion {
        return Justificacion(
            fecha = fecha?.trim(),
            estado = estado?.trim(),
            accion = accion?.trim(),
        )
    }
}