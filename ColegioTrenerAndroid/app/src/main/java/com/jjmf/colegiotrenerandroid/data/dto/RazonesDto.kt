package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Carnet
import com.jjmf.colegiotrenerandroid.domain.model.Razones

data class RazonesDto(
    @SerializedName("idtipo") val idtipo: String?,
    @SerializedName("descrip") val descrip: String?
) {
    fun toDomain(): Razones {
        return Razones(
            idtipo = idtipo?.trim(),
            descrip = descrip?.trim(),
        )
    }
}
