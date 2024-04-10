package com.jjmf.colegiotrenerandroid.data.dto.request

import com.google.gson.annotations.SerializedName


data class GrabarAutorizacionRequest(
    val idpermiso: String,
    val ctamae: String,
    val ctacli: String,
    val autorizo: String
)

data class GrabarAutorizacionResponse(
    @SerializedName("AutorizarAlumnoResult") val result:String?
)