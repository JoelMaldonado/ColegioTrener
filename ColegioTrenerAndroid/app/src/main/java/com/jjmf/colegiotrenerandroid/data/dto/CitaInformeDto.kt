package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.CitaInforme

data class CitaInformeDto(
    @SerializedName("nalumno") val nalumno: String?,
    @SerializedName("clase") val clase: String?,
    @SerializedName("fechacita") val fechacita: String?,
    @SerializedName("horario") val horario: String?,
    @SerializedName("observa") val observa: String?
) {
    fun toDomain(): CitaInforme {
        return CitaInforme(
            nalumno = nalumno?.trim(),
            clase = clase?.trim(),
            fechacita = fechacita?.trim(),
            horario = horario?.trim(),
            observa = observa?.trim()
        )
    }
}
