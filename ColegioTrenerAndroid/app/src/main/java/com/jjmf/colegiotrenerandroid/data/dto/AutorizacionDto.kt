package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Autorizacion

data class AutorizacionDto(
    @SerializedName("idautorizacion") val idautorizacion: String?,
    @SerializedName("autorizacion") val autorizacion: String?,
    @SerializedName("fechaven") val fechaven: String?
) {
    fun toDomain(): Autorizacion {
        return Autorizacion(
            idautorizacion = idautorizacion?.trim(),
            autorizacion = autorizacion?.trim(),
            fechaven = fechaven?.trim()
        )
    }
}