package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Asistencia

data class AsistenciaDto(
    @SerializedName("fecha") val fecha: String?,
    @SerializedName("trimestre") val trimestre: String?,
    @SerializedName("asistio") val asistio: Double?,
    @SerializedName("tardanza") val tardanza: Double?,
    @SerializedName("justificada") val justificada: Double?,
    @SerializedName("injustificada") val injustificada: Double?,
) {
    fun toDomain(): Asistencia {
        return Asistencia(
            fecha = fecha?.trim(),
            trimestre = trimestre?.trim(),
            asistio = asistio ?: 0.0,
            tardanza = tardanza ?: 0.0,
            justificada = justificada ?: 0.0,
            injustificada = injustificada ?: 0.0
        )
    }
}
