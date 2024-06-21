package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.DiariaAcumulada.DiariaAcumuladaViewModel
import com.jjmf.colegiotrenerandroid.ui.theme.ColorGreen
import com.jjmf.colegiotrenerandroid.ui.theme.ColorPurple
import com.jjmf.colegiotrenerandroid.ui.theme.ColorRed
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorYellow
import com.jjmf.colegiotrenerandroid.util.capitalize
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun CalendarioAsistencia(
    cal: CalendarState,
    cal2: MutableState<LocalDate>,
    viewModel: DiariaAcumuladaViewModel = hiltViewModel()
) {

    val coroutine = rememberCoroutineScope()

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        HorizontalCalendar(
            modifier = Modifier.fillMaxWidth(),
            state = cal,
            dayContent = { day ->
                Column(
                    modifier = Modifier.aspectRatio(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = day.date.dayOfMonth.toString(),
                        textAlign = TextAlign.Center
                    )
                    viewModel.list.find { it.localDate == day.date }?.let {

                        val color = when (it.leyendapp) {
                            "Tardanza" -> ColorYellow
                            "Justificada" -> ColorGreen
                            "Injustificada" -> ColorRed
                            "Asesoria" -> ColorPurple
                            else -> Color.Transparent
                        }

                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(color)
                        )
                    }
                }
            },
            userScrollEnabled = false,
            monthHeader = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ColorT1),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {
                            coroutine.launch {
                                cal.animateScrollToMonth(
                                    cal.firstVisibleMonth
                                        .yearMonth
                                        .minusMonths(1)
                                )
                            }

                            if (cal2.value.monthValue > cal.startMonth.monthValue) {
                                cal2.value = cal2.value.minusMonths(1)
                                viewModel.getList(
                                    year = cal2.value.year.toString(),
                                    month = cal2.value.monthValue.toString(),
                                    ctacli = viewModel.ctacli
                                )
                            }

                            viewModel.getList(
                                year = cal.firstVisibleMonth.yearMonth.year.toString(),
                                month = (cal.firstVisibleMonth.yearMonth.month.value - 1).toString(),
                                ctacli = viewModel.ctacli
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                            contentDescription = null
                        )
                    }

                    Text(
                        text = it.yearMonth.month.getDisplayName(
                            TextStyle.FULL,
                            Locale.getDefault()
                        ).capitalize()
                    )

                    IconButton(
                        onClick = {
                            coroutine.launch {
                                cal.animateScrollToMonth(
                                    cal.firstVisibleMonth.yearMonth.plusMonths(
                                        1
                                    )
                                )
                            }

                            if (cal2.value.monthValue < cal.endMonth.monthValue) {
                                cal2.value = cal2.value.plusMonths(1)
                                viewModel.getList(
                                    year = cal2.value.year.toString(),
                                    month = cal2.value.monthValue.toString(),
                                    ctacli = viewModel.ctacli
                                )
                            }
                            viewModel.getList(
                                year = cal.firstVisibleMonth.yearMonth.year.toString(),
                                month = (cal.firstVisibleMonth.yearMonth.month.value - 1).toString(),
                                ctacli = viewModel.ctacli
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                            contentDescription = null
                        )
                    }
                }
            },
            monthBody = { i, calendario ->
                if (viewModel.isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                        return@HorizontalCalendar
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    listOf("Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab").forEach {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp),
                            text = it,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                calendario()
            }
        )
    }
}