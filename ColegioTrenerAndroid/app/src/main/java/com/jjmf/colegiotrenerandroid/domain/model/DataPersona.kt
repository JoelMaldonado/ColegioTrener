package com.jjmf.colegiotrenerandroid.domain.model

import com.jjmf.colegiotrenerandroid.util.enums.TipoFamiliar
import java.util.Date

data class DataPersona(
    var tipo: TipoFamiliar,
    var nombre: String?,
    var alias: String?,
    var apepat: String?,
    var apemat: String?,
    var tipodoc: String?,
    var documento: String?,
    var fechanacimiento: Date,
    var distrito: String?,
    var direccion: String?,
    var celular: String?,
    var telefono: String?,
    var empresa: String?,
    var cargo: String?,
    var telefempresa: String?,
    var e_mailp: String?
)