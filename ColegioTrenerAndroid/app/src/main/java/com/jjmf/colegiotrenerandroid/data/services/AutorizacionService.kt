package com.jjmf.colegiotrenerandroid.data.services

import com.jjmf.colegiotrenerandroid.data.dto.request.GrabarAutorizacionRequest
import com.jjmf.colegiotrenerandroid.data.dto.request.GrabarAutorizacionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AutorizacionService {

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getAutorizaciones/{ctamae},{estado}")
    suspend fun listarAutorizaciones(
        @Path("ctamae") ctamae: String,
        @Path("estado") estado: String,
        @Header("Authorization") token: String
    ): Response<String>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getAlumnosyAutorizacion/{idPermiso},{ctamae}")
    suspend fun estado(
        @Path("idPermiso") idPermiso: String,
        @Path("ctamae") ctamae: String,
        @Header("Authorization") token: String
    ): Response<String>

    @POST("PublicacionFox/TrenerWCFOX.svc/Trener/AutorizarAlumno")
    suspend fun grabar(
        @Body request: GrabarAutorizacionRequest,
        @Header("Authorization") token: String
    ): Response<GrabarAutorizacionResponse>


}