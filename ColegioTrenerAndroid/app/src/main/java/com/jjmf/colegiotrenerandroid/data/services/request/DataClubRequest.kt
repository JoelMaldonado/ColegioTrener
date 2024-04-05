package com.jjmf.colegiotrenerandroid.data.services.request

import com.google.gson.annotations.SerializedName


data class DataClubRequest(
    val accion:String,
    val ctamae:String? = null,
    val codClub:String,
    val codParentesco: String,
    val nroCarnet:String
)

data class AddClubResponse(
    @SerializedName("crudClubFamResult") val crudClubFamResult:String?
)