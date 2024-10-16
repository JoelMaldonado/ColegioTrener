package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.data.services.AsistenciaService
import com.jjmf.colegiotrenerandroid.domain.model.Justificacion
import com.jjmf.colegiotrenerandroid.domain.repository.JustificacionRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import javax.inject.Inject
import com.jjmf.colegiotrenerandroid.core.*
import com.jjmf.colegiotrenerandroid.data.dto.CarnetDto
import com.jjmf.colegiotrenerandroid.data.dto.GrabarJustificacionDto
import com.jjmf.colegiotrenerandroid.data.dto.GrabarJustificacionResponse
import com.jjmf.colegiotrenerandroid.data.dto.JustificacionDto
import com.jjmf.colegiotrenerandroid.data.dto.RazonesDto
import com.jjmf.colegiotrenerandroid.data.dto.request.GrabarJustificacionRequest
import com.jjmf.colegiotrenerandroid.domain.model.Razones

class JustificacionRepositoryImp @Inject constructor(
    private val api: AsistenciaService,
    private val token: TokenUseCase,
    private val prefs: Preferencias
) : JustificacionRepository {
    override suspend fun getJustificaciones(ctaCli: String, year:String): Result<List<Justificacion>> {
        return try {
            val call = api.obtenerInasistencia(
                anio = year,
                ctacli = ctaCli,
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<JustificacionDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun getRazones(): Result<List<Razones>> {
        return try {
            val call = api.obtenerRazones(
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<RazonesDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun saveJustificacion(
        fecha:String,
        ctaCli:String,
        idRazon:String,
        comentario:String,
        fileName:String
        ): Result<String> {
        return try {
            println(fileName)
            val call = api.grabarJustificacion(
                GrabarJustificacionRequest(
                    fecha = fecha,
                    ctacli = ctaCli,
                    ctamae = prefs.getCtamae(),
                    idrazon = idRazon,
                    comentario = comentario,
                    filename = fileName
                ),
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<GrabarJustificacionDto>(call.body()?.registrarJustificacionResult)
                if(body.status == 1){
                    Result.Correcto(body.message)
                }else{
                    Result.Error(body.message)
                }
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}