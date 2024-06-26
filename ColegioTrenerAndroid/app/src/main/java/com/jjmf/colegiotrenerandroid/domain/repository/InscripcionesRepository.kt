package com.jjmf.colegiotrenerandroid.domain.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Inscripcion

interface InscripcionesRepository {
    suspend fun getListInscripciones(idHijo: String): Result<List<Inscripcion>>

    suspend fun save(
        ctacli: String,
        codTipoInscripcion: String,
        codInscripcion: String
    ): Result<String>
}