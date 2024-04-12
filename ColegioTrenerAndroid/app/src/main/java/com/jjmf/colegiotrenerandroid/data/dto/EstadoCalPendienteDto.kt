package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.EstadoCalPendiente
import com.jjmf.colegiotrenerandroid.util.toDate
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

data class EstadoCalPendienteDto(
    @SerializedName("fechaasignacion") val fechaasignacion: String?,
    @SerializedName("estado") val estado: String?,
    @SerializedName("cantidad") val cantidad: Double?
) {
    fun toDomain(): EstadoCalPendiente {
        val date = fechaasignacion?.toDate() ?: Date()
        val localDate = Instant.ofEpochMilli(date.time)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        return EstadoCalPendiente(
            fechaasignacion = localDate,
            estado = estado?.trim(),
            cantidad = cantidad ?: 0.0
        )
    }
}