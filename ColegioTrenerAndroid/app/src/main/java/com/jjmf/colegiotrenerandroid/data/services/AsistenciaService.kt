package com.jjmf.colegiotrenerandroid.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
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

}