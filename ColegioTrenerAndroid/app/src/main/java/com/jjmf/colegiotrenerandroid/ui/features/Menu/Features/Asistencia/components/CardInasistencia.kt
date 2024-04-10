package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.Asistencia
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import kotlin.math.roundToInt


@Composable
fun CardInasistencia(asistencia: Asistencia) {

    val textStyle = TextStyle(
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )

    CardPago(
        title = "Total Acumulado",
        label = "${asistencia.trimestre}"
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Inasistencia ${asistencia.asistio.roundToInt()}",
                    modifier = Modifier.weight(1f),
                    style = textStyle
                )
                VerticalDivider()
                Text(
                    text = "Tardanza ${asistencia.tardanza.roundToInt()}",
                    modifier = Modifier.weight(1f),
                    style = textStyle
                )
            }

            HorizontalDivider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "I. Justificada ${asistencia.justificada.roundToInt()}",
                    modifier = Modifier.weight(1f),
                    style = textStyle
                )

                VerticalDivider()

                Text(
                    text = "I. Injustificada ${asistencia.injustificada.roundToInt()}",
                    modifier = Modifier.weight(1f),
                    style = textStyle
                )

            }
        }
    }
}