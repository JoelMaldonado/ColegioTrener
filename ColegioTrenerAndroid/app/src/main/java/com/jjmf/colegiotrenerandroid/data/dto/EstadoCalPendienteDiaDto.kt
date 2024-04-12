package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.EstadoCalPendienteDia
import com.jjmf.colegiotrenerandroid.util.toDate
import java.util.Date

data class EstadoCalPendienteDiaDto(
    @SerializedName("fecpro") val fecpro: String?,
    @SerializedName("curso") val curso: String?,
    @SerializedName("tarea") val tarea: String?,
    @SerializedName("estado") val estado: String?,
    @SerializedName("fechaasignacion") val fechaasignacion: String?,
    @SerializedName("fechaentrega") val fechaentrega: String?
) {
    fun toDomain(): EstadoCalPendienteDia {
        return EstadoCalPendienteDia(
            fecpro = fecpro?.toDate(),
            curso = curso?.trim(),
            tarea = tarea?.trim(),
            estado = estado?.trim(),
            fechaasignacion = fechaasignacion?.toDate(),
            fechaentrega = fechaentrega?.toDate()
        )
    }
}