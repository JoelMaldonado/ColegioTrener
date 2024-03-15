package com.jjmf.colegiotrenerandroid.data.services.request

import com.google.gson.annotations.SerializedName


data class AuthRequest(
    val usuario: String,
    val contrasenia: String
)

data class AuthResponse(
    @SerializedName("NOMBRE_USUARIO") val nombre:String?,
    @SerializedName("CONTRASENIA") val clave:String?,
    @SerializedName("mensajeResultado") val resultado:String?,
    @SerializedName("mensajeCodigo") val codigo:Int?
)