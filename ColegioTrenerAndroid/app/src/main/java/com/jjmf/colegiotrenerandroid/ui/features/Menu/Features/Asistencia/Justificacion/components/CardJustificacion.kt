package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.Justificacion
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones.components.ItemInscripcion
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1

@Composable
fun CardJustificacion(
    data: List<Justificacion>,
    onClick:(Justificacion)->Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorT1),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Inasistencias injustificadas",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(ColorS1)
                        .border(width = 1.dp, color = Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Fecha", color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(ColorS1)
                        .border(width = 1.dp, color = Color.Black),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = "Estado", color = Color.White)
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(ColorS1)
                        .border(width = 1.dp, color = Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Acción", color = Color.White)
                }
            }
            data.forEach {
                ItemJustificacion(
                    justificacion = it,
                    onClick = {
                        onClick(it)
                    }
                )
            }
        }
    }
}