package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Asistencia
import com.jjmf.colegiotrenerandroid.domain.model.Carnet

data class CarnetDto(
    @SerializedName("linkVista") val linkVista: String?,
    @SerializedName("linkDescarga") val linkDescarga: String?
) {
    fun toDomain(): Carnet {
        return Carnet(
            linkVista = linkVista?.trim(),
            linkDescarga = linkDescarga?.trim(),
        )
    }
}