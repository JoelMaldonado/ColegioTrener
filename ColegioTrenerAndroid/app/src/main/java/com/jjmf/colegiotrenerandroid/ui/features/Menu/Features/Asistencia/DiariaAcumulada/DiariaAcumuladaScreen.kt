package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.DiariaAcumulada

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.components.CalendarioAsistencia
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.components.CardInasistencia
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.components.LeyendaAsistencia
import com.kizitonwose.calendar.compose.rememberCalendarState
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun DiariaAcumuladaScreen(
    viewModel: DiariaAcumuladaViewModel = hiltViewModel()
) {


    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(24) }
    val endMonth = remember { currentMonth.plusMonths(0) }

    val cal = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth
    )

    val cal2 = remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {

        SelectHijo(
            click = {
                viewModel.ctacli = it
                viewModel.getList(
                    year = cal.firstVisibleMonth.yearMonth.year.toString(),
                    month = cal.firstVisibleMonth.yearMonth.monthValue.toString(),
                    ctacli = it
                )
                viewModel.getTotalMes(viewModel.currentMonth.value, it)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            CalendarioAsistencia(
                cal = cal,
                cal2 = cal2,
            )

            LeyendaAsistencia()

            viewModel.asistencia?.let {
                CardInasistencia(
                    asistencia = it
                )
            }
        }
    }

}

