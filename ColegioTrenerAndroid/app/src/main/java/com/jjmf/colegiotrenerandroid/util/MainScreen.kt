package com.jjmf.colegiotrenerandroid.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jjmf.colegiotrenerandroid.ui.theme.ColorFondo
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun Prueba() {
    val text = remember { mutableStateOf("") }
    val focus = remember { FocusRequester() }

    val focusRequester = remember { FocusRequester() }
    val color = remember { mutableStateOf(Color.Black) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(32.dp)
    ) {
        Box(
            Modifier
                .size(50.dp)
                .background(color.value)
        )
        OutlinedTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    color.value = if (it.isFocused) Color.Green else Color.Red
                },
            value = text.value, onValueChange = { text.value = it },
            colors = TextFieldDefaults.colors(

            )
        )
        OutlinedTextField(
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    color.value = if (it.isFocused) Color.Yellow else Color.Red
                },
            value = text.value, onValueChange = { text.value = it }
        )
    }
}

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