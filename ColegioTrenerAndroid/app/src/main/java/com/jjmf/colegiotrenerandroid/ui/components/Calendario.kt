package com.jjmf.colegiotrenerandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.colegiotrenerandroid.domain.model.Inasistencia
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@Composable
fun Calendario(
    fecha: MutableState<LocalDate>,
    list: List<Pair<String, LocalDate>>,
    click: () -> Unit
) {


    val daysOfWeek = listOf("Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom")

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
                        fecha.value = fecha.value.minusMonths(1)
                        click()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = null
                    )
                }

                Text(text = fecha.value.format(DateTimeFormatter.ofPattern("MMMM")))

                IconButton(
                    onClick = {
                        fecha.value = fecha.value.plusMonths(1)
                        click()
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
                    daysOfWeek.forEach { day ->
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
                            currentMonth = fecha.value,
                            list = list
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun WeekRow(
    week: Int,
    currentMonth: LocalDate,
    list: List<Pair<String, LocalDate>>
) {
    val firstDayOfMonth = currentMonth.with(TemporalAdjusters.firstDayOfMonth())
    val startDate = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        .plusDays((week * 9).toLong())
    val formatter = DateTimeFormatter.ofPattern("dd")
    val daysInWeek = (0..6).map { startDate.plusDays(it.toLong()) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        daysInWeek.forEach { day ->
            if (day.month == firstDayOfMonth.month && day >= firstDayOfMonth) {

                val find = list.find { it.second == day }

                val color = when (find?.first) {
                    "J" -> Color.Yellow
                    "X" -> Color.Red
                    else -> Color.Transparent
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = formatter.format(day),
                        color = if (day.month == firstDayOfMonth.month) Color.Black else Color.Gray,
                        modifier = Modifier
                            .padding(4.dp)
                    )

                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            } else {
                Text(
                    text = "",
                    color = if (day.month == firstDayOfMonth.month) Color.Black else Color.Gray,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                )
            }
        }
    }
}