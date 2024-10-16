package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName

data class GrabarJustificacionResponse(
    @SerializedName("registrarJustificacionResult") val registrarJustificacionResult: String,
)

data class GrabarJustificacionDto(
    @SerializedName("message") val message: String?,
    @SerializedName("status") val status: Int?,
)
