package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Incumplimientos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.Incumplimiento
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.util.format


@Composable
fun ItemTareaIncumplida(it: Incumplimiento) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorT1)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "${it.abrevactualmod}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "${it.leyenda1}",
                color = Color.White,
                fontSize = 14.sp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(6.dp)
        ) {

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Tarea: ")
                    }
                    append("${it.destar}")
                },
                fontSize = 12.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    modifier = Modifier.weight(1f),
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Fecha dejada: ")
                        }
                        append(it.fectar.format())
                    },
                    fontSize = 12.sp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Fecha revisión: ")
                        }
                        append(it.cumtar.format())
                    },
                    fontSize = 12.sp
                )
            }
        }
    }
}