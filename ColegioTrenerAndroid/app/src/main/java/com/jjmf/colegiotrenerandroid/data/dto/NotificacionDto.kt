package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Notificacion
import com.jjmf.colegiotrenerandroid.util.toDate
import java.util.Date


data class NotificacionDto(
    @SerializedName("idrecordatorio") val idrecordatorio: Int?,
    @SerializedName("anoaca") val anoaca: String?,
    @SerializedName("titulo") val titulo: String?,
    @SerializedName("descripcion") val descripcion: String?,
    @SerializedName("vinculo") val vinculo: String?,
    @SerializedName("usupro") val usupro: String?,
    @SerializedName("fecpro") val fecpro: String?
) {
    fun toDomain(): Notificacion {
        return Notificacion(
            idrecordatorio = idrecordatorio,
            anoaca = anoaca?.trim(),
            titulo = titulo?.trim(),
            descripcion = descripcion?.trim(),
            vinculo = vinculo?.trim(),
            usupro = usupro?.trim(),
            fecpro = fecpro?.trim()?.toDate() ?: Date()
        )
    }
}