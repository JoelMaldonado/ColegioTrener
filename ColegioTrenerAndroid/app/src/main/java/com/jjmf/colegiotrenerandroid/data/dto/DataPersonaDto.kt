package com.jjmf.colegiotrenerandroid.data.dto

import com.jjmf.colegiotrenerandroid.domain.model.DataPersona
import com.jjmf.colegiotrenerandroid.util.enums.TipoFamiliar
import com.jjmf.colegiotrenerandroid.util.toDate
import java.util.Date

data class DataPersonaDto(
    var tipo: String? = null,
    var nombre: String? = null,
    var alias: String? = null,
    var apepat: String? = null,
    var apemat: String? = null,
    var tipodoc: String? = null,
    var documento: String? = null,
    var fechanacimiento: String? = null,
    var distrito: String? = null,
    var direccion: String? = null,
    var celular: String? = null,
    var telefono: String? = null,
    var empresa: String? = null,
    var cargo: String? = null,
    var telefempresa: String? = null,
    var e_mailp: String? = null
) {
    fun toDomain(): DataPersona {

        val tipoFam: TipoFamiliar = when (this.tipo?.trim()) {
            "PADRE" -> TipoFamiliar.Padre
            "MADRE" -> TipoFamiliar.Madre
            "APODERADO" -> TipoFamiliar.Apoderado
            else -> throw IllegalArgumentException("Tipo de usuario no válido")
        }

        return DataPersona(
            tipo = tipoFam,
            nombre = this.nombre?.trim(),
            alias = this.alias?.trim(),
            apepat = this.apepat?.trim(),
            apemat = this.apemat?.trim(),
            tipodoc = this.tipodoc?.trim(),
            documento = this.documento?.trim(),
            fechanacimiento = this.fechanacimiento?.trim()?.toDate() ?: Date(),
            distrito = this.distrito?.trim(),
            direccion = this.direccion?.trim(),
            celular = this.celular?.trim(),
            telefono = this.telefono?.trim(),
            empresa = this.empresa?.trim(),
            cargo = this.cargo?.trim(),
            telefempresa = this.telefempresa?.trim(),
            e_mailp = this.e_mailp?.trim()
        )
    }
}
