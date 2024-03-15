package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.DataClub

data class DataClubDto(
    @SerializedName("codvinculo") val codvinculo: String?,
    @SerializedName("vinculo") val vinculo: String?,
    @SerializedName("codclub") val codclub: String?,
    @SerializedName("club") val club: String?,
    @SerializedName("nrocar") val nrocar: String?
) {
    fun toDomain(): DataClub {
        return DataClub(
            codvinculo = codvinculo?.trim(),
            vinculo = vinculo?.trim(),
            codclub = codclub?.trim(),
            club = club?.trim(),
            nrocar = nrocar?.trim()
        )
    }
}
