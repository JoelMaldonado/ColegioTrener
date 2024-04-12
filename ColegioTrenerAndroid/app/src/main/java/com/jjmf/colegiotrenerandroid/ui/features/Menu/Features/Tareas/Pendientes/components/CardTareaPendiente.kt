package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Pendientes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jjmf.colegiotrenerandroid.domain.model.EstadoCalPendienteDia
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.util.format


@Composable
fun CardTareaPendiente(
    list: List<EstadoCalPendienteDia>
) {
    val fecha = list.firstOrNull()?.fecpro

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(ColorT1.copy(0.4f))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorP1)
                    .padding(vertical = 4.dp),
                text = "${fecha?.format("dd")} ${fecha?.format("MMMM")} ${fecha?.format("EEEE")}",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            list.forEach {
                ItemTareaPendiente(it)
            }
        }
    }
}