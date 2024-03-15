package com.jjmf.colegiotrenerandroid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.util.format
import java.time.LocalDate

@Composable
fun SelectYear(
    click: (LocalDate)->Unit
) {

    val year = remember { mutableStateOf(LocalDate.now()) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        IconButton(
            onClick = {
                year.value = year.value.minusYears(1)
                click(year.value)
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = null,
                tint = ColorP1
            )
        }

        Text(
            text = year.value.format("yyyy"),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = ColorP1
        )

        IconButton(
            onClick = {
                year.value = year.value.plusYears(1)
                click(year.value)
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = ColorP1
            )
        }
    }

}