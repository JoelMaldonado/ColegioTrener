package com.jjmf.colegiotrenerandroid.domain.model

import androidx.compose.ui.graphics.Color
import com.jjmf.colegiotrenerandroid.ui.theme.ColorGreen
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorYellow
import java.time.LocalDate

data class EstadoCalPendiente(
    val fechaasignacion: LocalDate?,
    val estado: String?,
    val cantidad: Double
) {

    fun getColor() : Color? {
        return when (estado) {
            "Pendiente" -> ColorYellow
            "Revisado" -> ColorGreen
            "No hizo tarea" -> ColorT1
            else -> null
        }
    }
}