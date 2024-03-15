package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Incumplimientos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.colegiotrenerandroid.domain.model.Inasistencia
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@Composable
fun TareasIncumplimientosScreen() {

}

@Composable
fun WeekRow(
    week: Int,
    currentMonth: LocalDate,
    list: List<Inasistencia>
) {
    val firstDayOfMonth = currentMonth.with(TemporalAdjusters.firstDayOfMonth())
    val startDate = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).plusDays((week * 9).toLong())
    val formatter = DateTimeFormatter.ofPattern("dd")
    val daysInWeek = (0..6).map { startDate.plusDays(it.toLong()) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        daysInWeek.forEach { day ->
            if (day.month == firstDayOfMonth.month && day >= firstDayOfMonth) {

                val find = list.find { it.localDate == day }

                val color = when (find?.leyenda) {
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
