package com.jjmf.colegiotrenerandroid.data.services

import com.jjmf.colegiotrenerandroid.data.dto.GrabarJustificacionResponse
import com.jjmf.colegiotrenerandroid.data.dto.request.GrabarAutorizacionResponse
import com.jjmf.colegiotrenerandroid.data.dto.request.GrabarJustificacionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AsistenciaService {

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getAsistenciaTotalMes/{year},{month},{ctacli}")
    suspend fun listarAsistenciasPorMes(
        @Path("year") year:String,
        @Path("month") month:String,
        @Path("ctacli") ctacli:String,
        @Header("Authorization") token: String
    ): Response<String>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getAsistenciaXMes/{year},{month},{ctacli}")
    suspend fun listarInasistenciasPorAlumno(
        @Path("year") year:String,
        @Path("month") month:String,
        @Path("ctacli") ctacli:String,
        @Header("Authorization") token: String
    ): Response<String>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/fotoCarnetAlumno/{ctacli}")
    suspend fun obtenerCarnet(
        @Path("ctacli") ctacli:String,
        @Header("Authorization") token: String
    ): Response<String>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getInasistenciasInjustificadas/{anio},{ctacli}")
    suspend fun obtenerInasistencia(
        @Path("anio") anio:String,
        @Path("ctacli") ctacli:String,
        @Header("Authorization") token: String
    ): Response<String>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getRazonesJustificar")
    suspend fun obtenerRazones(
        @Header("Authorization") token: String
    ): Response<String>

    @POST("PublicacionFox/TrenerWCFOX.svc/Trener/registrarJustificacion")
    suspend fun grabarJustificacion(
        @Body request: GrabarJustificacionRequest,
        @Header("Authorization") token: String
    ): Response<GrabarJustificacionResponse>



}