package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Incumplimientos

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1

@Preview
@Composable
fun TareasIncumplimientosScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        /*
        SelectHijo(
            click = {

            }
        )
        */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, color = Color.Black, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(ColorS1.copy(0.4f)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .background(ColorS1)
                        .padding(vertical = 2.dp, horizontal = 8.dp),
                    text = "Total Acumulado",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = "Trimestre",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "3",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            CardTareaIncumplida()
            CardTareaIncumplida()
        }
    }
}

@Composable
fun CardTareaIncumplida() {
    val bool = remember { mutableStateOf(false) }
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorP1)
                    .clickable { bool.value = !bool.value }
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Semana 33 (20/11/2023)", color = Color.White, fontSize = 14.sp)
                Text(text = "tareas incumplidas: 2", color = Color.White, fontSize = 14.sp)
            }
            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(), visible = bool.value
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ItemTareaIncumplida()
                    ItemTareaIncumplida()
                }
            }
        }
    }
}

@Composable
fun ItemTareaIncumplida() {
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
            Text(text = "COM", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "No hizo Tarea",
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
                    append("Terminar presión de grupo y Los cachorros")
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
                        append("28/11/2023")
                    },
                    fontSize = 12.sp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Fecha revisión: ")
                        }
                        append("30/11/2023")
                    },
                    fontSize = 12.sp
                )
            }
        }
    }
}

