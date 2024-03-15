package com.jjmf.colegiotrenerandroid.data.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Inasistencia
import java.time.LocalDate

data class InasistenciaDto(
    @SerializedName("anoaca") val anoaca: String?,
    @SerializedName("mes") val mes: Int?,
    @SerializedName("dia") val dia: Int?,
    @SerializedName("ctacli") val ctacli: String?,
    @SerializedName("codgra") val codgra: String?,
    @SerializedName("ctaemp") val ctaemp: String?,
    @SerializedName("semana") val semana: Int?,
    @SerializedName("leyenda") val leyenda: String?,
    @SerializedName("leyendapp") val leyendapp: String?
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun toDomain(): Inasistencia {
        return Inasistencia(
            anoaca = anoaca?.trim(),
            mes = mes,
            dia = dia,
            ctacli = ctacli?.trim(),
            codgra = codgra?.trim(),
            ctaemp = ctaemp?.trim(),
            semana = semana,
            leyenda = leyenda?.trim(),
            leyendapp = leyendapp?.trim(),
            localDate = LocalDate.of(anoaca?.toInt()!!, mes!!, dia!!)
        )
    }
}