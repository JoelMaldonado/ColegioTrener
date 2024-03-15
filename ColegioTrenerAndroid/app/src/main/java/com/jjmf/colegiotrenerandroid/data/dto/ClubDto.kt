package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Club

data class ClubDto(
    @SerializedName("codigo") val codigo: String?,
    @SerializedName("descrip") val descrip: String?
) {
    fun toDomain(): Club {
        return Club(
            codigo = codigo?.trim(),
            descrip = descrip?.trim()
        )
    }
}
