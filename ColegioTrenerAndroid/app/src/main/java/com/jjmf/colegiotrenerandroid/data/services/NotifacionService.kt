package com.jjmf.colegiotrenerandroid.data.services

import com.jjmf.colegiotrenerandroid.data.dto.NotificacionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface NotifacionService {

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getRecordatoriosByAnoacaCtacli/{ctacli}")
    suspend fun getNotificaciones(
        @Path("ctacli") ctacli: String,
        @Header("Authorization") token: String
    ): Response<String>

}