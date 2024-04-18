package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.EstadoAutorizacion

data class EstadoAutorizacionDto(
    @SerializedName("anoaca") val anoaca: String?,
    @SerializedName("ctacli") val ctacli: String?,
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("ctamae") val ctamae: String?,
    @SerializedName("codgra") val codgra: String?,
    @SerializedName("autorizo") val autorizo: Double?,
) {
    fun toDomain(): EstadoAutorizacion {
        return EstadoAutorizacion(
            anoaca = anoaca?.trim(),
            ctacli = ctacli?.trim(),
            nombre = nombre?.trim(),
            ctamae = ctamae?.trim(),
            codgra = codgra?.trim(),
            autorizo = autorizo == 0.0
        )
    }
}