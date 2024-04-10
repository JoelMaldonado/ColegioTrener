package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Incumplimientos.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.Incumplimiento
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1


@Composable
fun CardTareaIncumplida(
    list: List<Incumplimiento>
) {
    val bool = remember { mutableStateOf(false) }
    val first = list.firstOrNull()
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorP1)
                    .clickable { bool.value = !bool.value }
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Semana ${first?.semana}", color = Color.White, fontSize = 14.sp)
                Text(text = "tareas incumplidas: ${list.size}", color = Color.White, fontSize = 14.sp)
            }
            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(), visible = bool.value
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    list.forEach {
                        ItemTareaIncumplida(it)
                    }
                }
            }
        }
    }
}