package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.Pago
import com.jjmf.colegiotrenerandroid.util.toDate
import java.util.Date

data class PagoDto(
    @SerializedName("anoaca") val anoaca: String?,
    @SerializedName("codconcepto") val codconcepto: String?,
    @SerializedName("concepto") val concepto: String?,
    @SerializedName("fecven") val fecven: String?,
    @SerializedName("saldo") val saldo: Double?,
    @SerializedName("estado") val estado: String?,
    @SerializedName("tipdoc") val tipdoc: String?,
    @SerializedName("numdoc") val numdoc: String?,
    @SerializedName("mediopago") val mediopago: String?,
    @SerializedName("fecpag") val fecpag: String?,
    @SerializedName("importepagado") val importepagado: Double?,
    @SerializedName("mora") val mora: Double?,
) {
    fun toDomain(): Pago {
        return Pago(
            anoaca = anoaca?.trim(),
            codconcepto = codconcepto?.trim(),
            concepto = concepto?.trim(),
            fecven = fecven?.trim()?.toDate() ?: Date(),
            saldo = saldo ?: 0.0,
            estado = estado?.trim(),
            tipdoc = tipdoc?.trim(),
            numdoc = numdoc?.trim(),
            mediopago = mediopago?.trim(),
            fecpag = fecpag?.trim()?.toDate() ?: Date(),
            importepagado = importepagado ?: 0.0,
            mora = mora ?: 0.0
        )
    }
}