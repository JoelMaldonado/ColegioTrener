package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.DiariaAcumulada

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.Calendario
import com.jjmf.colegiotrenerandroid.ui.components.CircleText
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.theme.ColorGreen
import com.jjmf.colegiotrenerandroid.ui.theme.ColorPurple
import com.jjmf.colegiotrenerandroid.ui.theme.ColorRed
import com.jjmf.colegiotrenerandroid.ui.theme.ColorYellow

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

            Calendario(
                fecha = viewModel.currentMonth,
                list = viewModel.list.map { Pair(it.leyenda.toString(), it.localDate) },
                click = {
                    viewModel.getList(viewModel.currentMonth.value)
                    viewModel.getTotalMes(viewModel.currentMonth.value)
                },

            )

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