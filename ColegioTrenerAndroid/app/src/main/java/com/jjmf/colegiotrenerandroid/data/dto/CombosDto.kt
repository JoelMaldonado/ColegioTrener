package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Club
import com.jjmf.colegiotrenerandroid.domain.model.Distrito


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

data class DistritoDto(
    @SerializedName("coddis") val coddis: String?,
    @SerializedName("desdis") val desdis: String?,
    @SerializedName("abrdis") val abrdis: String?
) {
    fun toDomain(): Distrito {
        return Distrito(
            coddis = coddis?.trim(),
            desdis = desdis?.trim(),
            abrdis = abrdis?.trim()
        )
    }
}