package com.jjmf.colegiotrenerandroid.data.services.request

import com.google.gson.annotations.SerializedName


data class AddHijoRequest(
    val accion:String,
    val ctamae:String,
    val nombre:String,
    val fechaNac:String
)

data class AddHijoResponse(
    @SerializedName("crudHijoFamResult") val crudHijoFamResult:String
)