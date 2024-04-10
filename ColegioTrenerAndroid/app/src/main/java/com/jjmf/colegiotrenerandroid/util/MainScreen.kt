package com.jjmf.colegiotrenerandroid.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun MainScreen() {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(0) } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    val list = listOf(
        LocalDate.now()
    )

    HorizontalCalendar(
        modifier = Modifier.fillMaxWidth(),
        state = state,
        dayContent = { day ->
            Column(
                modifier = Modifier.aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = day.date.dayOfMonth.toString())
                list.find { it == day.date }?.let {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.Red)
                    )
                }
            }
        },
        monthHeader = {
            Text(text = it.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        },
        monthBody = { i, calendario ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab").forEach {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = it,
                        textAlign = TextAlign.Center
                    )
                }
            }
            calendario()
        }
    )
}