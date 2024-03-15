package com.jjmf.colegiotrenerandroid.data.dto

import com.google.gson.annotations.SerializedName
import com.jjmf.colegiotrenerandroid.domain.model.DataHijo
import com.jjmf.colegiotrenerandroid.util.toDate
import java.util.Date

data class DataHijoDto(
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("fechanacimiento") val fechaNac: String?
) {
    fun toDomain(): DataHijo {
        return DataHijo(
            nombre = nombre?.trim(),
            fechaNac = fechaNac?.toDate() ?: Date()
        )
    }
}