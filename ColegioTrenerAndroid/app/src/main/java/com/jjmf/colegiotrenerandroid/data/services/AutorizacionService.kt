package com.jjmf.colegiotrenerandroid.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface AutorizacionService {

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getAutorizaciones/{ctamae},{estado}")
    suspend fun listarAutorizaciones(
        @Path("ctamae") ctamae:String,
        @Path("estado") estado:String,
        @Header("Authorization") token:String
    ) : Response<String>
}