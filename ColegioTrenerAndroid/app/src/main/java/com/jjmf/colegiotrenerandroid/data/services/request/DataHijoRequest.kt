package com.jjmf.colegiotrenerandroid.data.services.request

import com.google.gson.annotations.SerializedName


data class DataHijoRequest(
    val accion:String,
    val ctamae:String? = null,
    val nombre:String,
    val fechaNac:String
)

data class AddHijoResponse(
    @SerializedName("crudHijoFamResult") val crudHijoFamResult:String
)