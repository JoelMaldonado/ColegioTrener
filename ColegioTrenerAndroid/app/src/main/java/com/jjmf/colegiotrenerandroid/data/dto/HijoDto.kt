package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Hijo

data class HijoDto(
    @SerializedName("ctacli") val ctacli: String?,
    @SerializedName("apepat") val apepat: String?,
    @SerializedName("apemat") val apemat: String?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("alias") val alias: String?,
    @SerializedName("dirfot") val dirfot: String?,
    @SerializedName("dirfotapp") val dirfotapp: String?,
    @SerializedName("param1") val param1: String?,
    @SerializedName("distrito") val distrito: String?,
    @SerializedName("anoaca") val anoaca: String?
) {
    fun toDomain(): Hijo {
        return Hijo(
            ctacli = ctacli?.trim(),
            apepat = apepat?.trim(),
            apemat = apemat?.trim(),
            nombre = nombre?.trim(),
            alias = alias?.trim(),
            dirfot = dirfot?.trim(),
            dirfotapp = dirfotapp?.trim(),
            param1 = param1?.trim(),
            distrito = distrito?.trim(),
            anoaca = anoaca?.trim()
        )
    }
}