package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.app.Preferencias
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.AutorizacionDto
import com.jjmf.colegiotrenerandroid.data.dto.EstadoAutorizacionDto
import com.jjmf.colegiotrenerandroid.data.services.AutorizacionService
import com.jjmf.colegiotrenerandroid.domain.model.Autorizacion
import com.jjmf.colegiotrenerandroid.domain.model.EstadoAutorizacion
import com.jjmf.colegiotrenerandroid.domain.repository.AutorizacionRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.Estado
import com.jjmf.colegiotrenerandroid.util.convertJson
import javax.inject.Inject

class AutorizacionRepositoryImpl @Inject constructor(
    private val api: AutorizacionService,
    private val token: TokenUseCase,
    private val prefs: Preferencias
) : AutorizacionRepository {
    override suspend fun listarAutorizaciones(
        estado: Estado
    ): Result<List<Autorizacion>> {
        return try {
            val call = api.listarAutorizaciones(
                ctamae = prefs.getUsuario().toString(),
                estado = estado.code,
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<AutorizacionDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun estado(idPermiso:String): Result<List<EstadoAutorizacion>> {
        return try {
            val call = api.estado(
                ctamae = prefs.getUsuario().toString(),
                idPermiso = idPermiso,
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<EstadoAutorizacionDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            } else {
                Result.Error(call.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}