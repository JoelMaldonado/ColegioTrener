package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.DiariaAcumulada

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Incumplimientos.WeekRow
import com.jjmf.colegiotrenerandroid.ui.theme.ColorGreen
import com.jjmf.colegiotrenerandroid.ui.theme.ColorPurple
import com.jjmf.colegiotrenerandroid.ui.theme.ColorRed
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorYellow
import java.time.format.DateTimeFormatter

@Composable
fun DiariaAcumuladaScreen(
    viewModel: DiariaAcumuladaViewModel = hiltViewModel()
) {

    val textStyle = TextStyle(
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        SelectHijo(click = {})
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ColorT1),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        IconButton(
                            onClick = {
                                viewModel.currentMonth = viewModel.currentMonth.minusMonths(1)
                                viewModel.getList(viewModel.currentMonth)
                                viewModel.getTotalMes(viewModel.currentMonth)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                                contentDescription = null
                            )
                        }

                        Text(text = viewModel.currentMonth.format(DateTimeFormatter.ofPattern("MMMM")))

                        IconButton(
                            onClick = {
                                viewModel.currentMonth = viewModel.currentMonth.plusMonths(1)
                                viewModel.getList(viewModel.currentMonth)
                                viewModel.getTotalMes(viewModel.currentMonth)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = null
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            viewModel.daysOfWeek.forEach { day ->
                                Text(
                                    text = day,
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(5) { week ->
                                WeekRow(
                                    week = week,
                                    currentMonth = viewModel.currentMonth,
                                    list = viewModel.list
                                )
                            }
                        }
                    }
                }
            }

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

            viewModel.asistencia?.let { asistencia ->

                CardPago(title = "Total Acumulado", label = "${asistencia.trimestre}") {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Inasistencia ${asistencia.asistio}",
                                modifier = Modifier.weight(1f),
                                style = textStyle
                            )
                            VerticalDivider()
                            Text(
                                text = "Tardanza ${asistencia.tardanza}",
                                modifier = Modifier.weight(1f),
                                style = textStyle
                            )
                        }
                        HorizontalDivider()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "I. Justificada ${asistencia.justificada}",
                                modifier = Modifier.weight(1f),
                                style = textStyle
                            )
                            VerticalDivider()
                            Text(
                                text = "I. Injustificada ${asistencia.injustificada}",
                                modifier = Modifier.weight(1f),
                                style = textStyle
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CircleText(
    text: String,
    color: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Circle(size = 10.dp, color = color)
        Text(text = text, fontSize = 12.sp)
    }
}

@Composable
fun Circle(size: Dp, color: Color) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}