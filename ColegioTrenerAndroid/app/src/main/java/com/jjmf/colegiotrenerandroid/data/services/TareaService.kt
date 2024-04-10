package com.jjmf.colegiotrenerandroid.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TareaService {
    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getInfoIncumplimiento/{ctacli}")
    suspend fun getListaIncumplimientos(
        @Path("ctacli") ctacli: String = "00002528",
        @Header("Authorization") token: String
    ): Response<String>

}