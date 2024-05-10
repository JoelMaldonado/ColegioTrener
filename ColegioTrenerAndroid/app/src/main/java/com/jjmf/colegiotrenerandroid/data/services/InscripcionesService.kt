package com.jjmf.colegiotrenerandroid.data.services

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface InscripcionesService {

    @GET("/PublicacionFox/TrenerWCFOX.svc/Trener/getInscripcionesAlumno/{idHijo}")
    suspend fun getListInscripciones(
        @Path("idHijo") idHijo: String,
        @Header("Authorization") token: String
    ): Response<String>

    @POST("/PublicacionFox/TrenerWCFOX.svc/Trener/inscribirAlumno")
    suspend fun insert(
        @Header("Authorization") token: String,
        @Body request: RequestInsertInscripcion
    ): Response<ResponseInsertInscripcion>
}

data class RequestInsertInscripcion(
    @SerializedName("ctamae") val ctamae:String,
    @SerializedName("ctacli") val ctacli:String,
    @SerializedName("codtipoinscripcion") val codtipoinscripcion:String,
    @SerializedName("codinscripcion") val codinscripcion:String
)

data class ResponseInsertInscripcion(
    @SerializedName("incribirAlumnoResult") val incribirAlumnoResult: String?
)