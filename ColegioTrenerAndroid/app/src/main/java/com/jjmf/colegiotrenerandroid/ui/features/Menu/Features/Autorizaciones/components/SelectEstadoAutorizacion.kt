package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.Estado
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1


@Composable
fun SelectEstadoAutorizacion(
    estado: Estado,
    click:(Estado)->Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, ColorT1, RoundedCornerShape(15.dp))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Estado",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        RadioButton(
            selected = estado == Estado.Activo,
            onClick = {
                click(Estado.Activo)
            }
        )
        Text(
            text = "Activos",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        RadioButton(
            selected = estado == Estado.Vencido,
            onClick = {
                click(Estado.Vencido)
            }
        )
        Text(
            text = "Vencidos",
            fontSize = 14.sp
        )
    }
}