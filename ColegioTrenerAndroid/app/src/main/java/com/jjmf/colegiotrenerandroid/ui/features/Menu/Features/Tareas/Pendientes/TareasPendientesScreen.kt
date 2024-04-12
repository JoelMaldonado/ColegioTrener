package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Pendientes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.CircleText
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Pendientes.components.CardTareaPendiente
import com.jjmf.colegiotrenerandroid.ui.theme.ColorGreen
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorYellow
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import kotlinx.coroutines.launch
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun TareasPendientesScreen(
    viewModel: TareasPendientesViewModel = hiltViewModel()
) {


    val coroutine = rememberCoroutineScope()
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(24) }
    val endMonth = remember { currentMonth.plusMonths(0) }

    val cal = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {

        SelectHijo(
            click = {
                viewModel.ctacli = it
                viewModel.listarDatosCalendario(
                    anio = cal.firstVisibleMonth.yearMonth.year.toString(),
                    mes = cal.firstVisibleMonth.yearMonth.monthValue.toString()
                )
            }
        )
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

                HorizontalCalendar(
                    modifier = Modifier.fillMaxWidth(),
                    state = cal,
                    dayContent = { day ->
                        Column(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .clickable {
                                           viewModel.selectDia(day.date)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = day.date.dayOfMonth.toString(),
                                textAlign = TextAlign.Center
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                viewModel.list.find { it.fechaasignacion == day.date }?.let {
                                    if (it.estado == "Tarea") {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .clip(CircleShape)
                                                .background(ColorT1)
                                        )
                                    }
                                    if (it.estado == "Pendiente") {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .clip(CircleShape)
                                                .background(ColorYellow)
                                        )
                                    }
                                    if (it.estado == "Revisado") {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .clip(CircleShape)
                                                .background(ColorGreen)
                                        )
                                    }
                                }
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
                                    viewModel.listarDatosCalendario(
                                        anio = cal.firstVisibleMonth.yearMonth.year.toString(),
                                        mes = (cal.firstVisibleMonth.yearMonth.month.value - 1).toString()
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
                                )
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
                                    viewModel.listarDatosCalendario(
                                        anio = cal.firstVisibleMonth.yearMonth.year.toString(),
                                        mes = (cal.firstVisibleMonth.yearMonth.month.value - 1).toString()
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CircleText(text = "Pendiente", color = ColorYellow)
                CircleText(text = "No hizo tarea", color = ColorT1)
                CircleText(text = "Revisado", color = ColorGreen)
            }

            viewModel.listDia?.let {
                CardTareaPendiente(it)
            }
        }

    }

}



