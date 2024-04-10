package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.EstadoAutorizacion
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1


@Composable
fun CardAutorizacionEstado(estado: EstadoAutorizacion) {

    CardPago(title = estado.nombre.toString()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = ColorP1
            )
            Text(
                text = "Codigo: ",
                color = ColorP1,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = estado.ctacli.toString(), fontSize = 12.sp)

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.DoorFront,
                contentDescription = null,
                tint = ColorP1
            )
            Text(
                text = "Clase: ",
                color = ColorP1,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = estado.codgra.toString(), fontSize = 12.sp)

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "¿Autorizo? ",
                fontSize = 12.sp,
                color = ColorP1,
                fontWeight = FontWeight.SemiBold
            )

            SwitchAutorizacion(
                bool = estado.autorizo,
                click = {

                }
            )

        }
    }
}
