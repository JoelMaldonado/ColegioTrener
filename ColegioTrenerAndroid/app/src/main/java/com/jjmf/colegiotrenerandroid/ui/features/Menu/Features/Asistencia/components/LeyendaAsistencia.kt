package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jjmf.colegiotrenerandroid.ui.components.CircleText
import com.jjmf.colegiotrenerandroid.ui.theme.ColorGreen
import com.jjmf.colegiotrenerandroid.ui.theme.ColorPurple
import com.jjmf.colegiotrenerandroid.ui.theme.ColorRed
import com.jjmf.colegiotrenerandroid.ui.theme.ColorYellow


@Composable
fun LeyendaAsistencia() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CircleText(text = "Tardanza", color = ColorYellow)
        CircleText(text = "I. Justificada", color = ColorGreen)
        CircleText(text = "I. Injustificada", color = ColorRed)
        CircleText(text = "Asesoría", color = ColorPurple)
    }

}