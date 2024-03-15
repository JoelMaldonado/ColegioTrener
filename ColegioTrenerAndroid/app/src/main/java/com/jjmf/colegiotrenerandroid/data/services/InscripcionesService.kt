package com.jjmf.colegiotrenerandroid.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface InscripcionesService {

    @GET("/PublicacionFox/TrenerWCFOX.svc/Trener/getInscripcionesAlumno/{idHijo}")
    suspend fun getListInscripciones(
        @Path("idHijo") idHijo: String,
        @Header("Authorization") token: String
    ): Response<String>

}