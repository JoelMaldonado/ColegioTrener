package com.jjmf.colegiotrenerandroid.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CitaInformeService {
    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getCitasEntregaInformes/{ctamae},{year},{trimestre}")
    suspend fun listarCitasInforme(
        @Path("ctamae") ctamae:String,
        @Path("year") year:String,
        @Path("trimestre") trimestre:String,
        @Header("Authorization") token: String
    ) : Response<String>
}