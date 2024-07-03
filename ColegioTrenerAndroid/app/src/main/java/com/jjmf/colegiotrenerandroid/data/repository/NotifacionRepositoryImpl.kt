package com.jjmf.colegiotrenerandroid.data.repository

import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.data.dto.NotificacionDto
import com.jjmf.colegiotrenerandroid.data.services.NotifacionService
import com.jjmf.colegiotrenerandroid.domain.model.Notificacion
import com.jjmf.colegiotrenerandroid.domain.repository.NotifacionRepository
import com.jjmf.colegiotrenerandroid.domain.usecase.TokenUseCase
import com.jjmf.colegiotrenerandroid.util.convertJson
import javax.inject.Inject

class NotifacionRepositoryImpl @Inject constructor(
    private val api: NotifacionService,
    private val token: TokenUseCase,
) : NotifacionRepository {
    override suspend fun getAll(ctacli: String): Result<List<Notificacion>> {
        return try {
            val call = api.getNotificaciones(
                ctacli = ctacli,
                token = token()
            )
            if (call.isSuccessful) {
                val body = convertJson<Array<NotificacionDto>>(call.body()).map { it.toDomain() }
                Result.Correcto(body)
            }
            else Result.Error(call.message())
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}