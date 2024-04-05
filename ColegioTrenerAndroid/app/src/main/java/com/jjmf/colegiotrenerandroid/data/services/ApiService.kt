package com.jjmf.colegiotrenerandroid.data.services

import com.jjmf.colegiotrenerandroid.data.services.request.DataClubRequest
import com.jjmf.colegiotrenerandroid.data.services.request.AddClubResponse
import com.jjmf.colegiotrenerandroid.data.services.request.DataHijoRequest
import com.jjmf.colegiotrenerandroid.data.services.request.AddHijoResponse
import com.jjmf.colegiotrenerandroid.data.services.request.AuthRequest
import com.jjmf.colegiotrenerandroid.data.services.request.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("PublicacionSQL/TrenerWCF.svc/Trener/autenticarApiTrener")
    suspend fun getToken(
        @Body request: AuthRequest
    ): Response<AuthResponse>

    @POST("PublicacionSQL/TrenerWCF.svc/Trener/autenticarCredenciales")
    suspend fun login(
        @Body request: AuthRequest,
        @Header("Authorization") token: String
    ): Response<AuthResponse>


    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getDatospadres/{ctli}")
    suspend fun data(
        @Header("Authorization") token: String,
        @Path("ctli") ctli: String
    ): Response<String>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getHijosFamilia/{ctmae}")
    suspend fun getDataHijos(
        @Header("Authorization") token: String,
        @Path("ctmae") ctmae: String
    ): Response<String>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getHijosActivosapp/{ctli}")
    suspend fun getHijosMatriculados(
        @Header("Authorization") token: String,
        @Path("ctli") ctli: String
    ): Response<String>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getClubesFamilia/{ctli}")
    suspend fun getDataClubes(
        @Header("Authorization") token: String,
        @Path("ctli") ctli: String
    ): Response<String>

    @POST("PublicacionFox/TrenerWCFOX.svc/Trener/crudHijoFam")
    suspend fun insertHijo(
        @Body request: DataHijoRequest,
        @Header("Authorization") token: String
    ): Response<AddHijoResponse>

    @POST("PublicacionFox/TrenerWCFOX.svc/Trener/crudClubFam")
    suspend fun insertClub(
        @Body request: DataClubRequest,
        @Header("Authorization") token: String
    ): Response<AddClubResponse>

    @GET("PublicacionFox/TrenerWCFOX.svc/Trener/getListaClubs")
    suspend fun getClubs(
        @Header("Authorization") token: String
    ): Response<String>
}