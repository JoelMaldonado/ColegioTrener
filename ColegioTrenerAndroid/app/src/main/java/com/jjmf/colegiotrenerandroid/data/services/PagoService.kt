package com.jjmf.colegiotrenerandroid.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PagoService {

    /** BASE_URL = "https://intranet.trener.edu.pe:8093/" **/

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getDeudasxAlumno/{ctacli},{year},Pendiente")
    suspend fun getPagosPendientes(
        @Path("ctacli") ctacli: String,
        @Path("year") year: String,
        @Header("Authorization") token: String
    ): Response<String>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getDeudasxAlumno/{ctacli},{year},Vencido")
    suspend fun getPagosVencidos(
        @Path("ctacli") ctacli: String,
        @Path("year") year: String,
        @Header("Authorization") token: String
    ): Response<String>

    @GET("/PublicacionFox/TrenerWCFOX.svc/Trener/getPagosxAlumno/{ctacli},{year}")
    suspend fun getPagosRealizados(
        @Path("ctacli") ctacli: String,
        @Path("year") year: String,
        @Header("Authorization") token: String
    ): Response<String>
}