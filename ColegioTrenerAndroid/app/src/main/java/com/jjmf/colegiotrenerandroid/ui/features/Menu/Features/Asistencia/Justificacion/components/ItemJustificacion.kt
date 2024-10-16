package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.Justificacion
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1

@Composable
fun ItemJustificacion(
    modifier: Modifier = Modifier,
    justificacion: Justificacion,
    onClick:(Justificacion)->Unit
) {

    Row {
        Box(
            modifier = modifier
                .weight(1f)
                .height(48.dp)
                .border(width = 1.dp, color = Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "${justificacion.fecha?.substring(8,10)}/${justificacion.fecha?.substring(5,7)}/${justificacion.fecha?.substring(0,4)}" ,
                fontSize = 14.sp)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .border(width = 1.dp, color = Color.Black),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = justificacion.estado ?: "",fontSize = 14.sp)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .border(width = 1.dp, color = Color.Black),
            contentAlignment = Alignment.Center
        ) {
            if (justificacion.accion.equals("Justifi")) {
                Button(
                    onClick = {
                        onClick(justificacion)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorS1
                    ),
                    modifier = Modifier.height(32.dp)
                ) {
                    Text(
                        text = "Justificar",
                        fontSize = 14.sp
                    )
                }
            } else {
                Text(text = justificacion.accion ?: "",fontSize = 14.sp)
            }
        }
    }

}