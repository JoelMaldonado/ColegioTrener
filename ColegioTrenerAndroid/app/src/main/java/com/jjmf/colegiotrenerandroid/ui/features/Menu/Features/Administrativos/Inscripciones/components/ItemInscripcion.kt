package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.Inscripcion
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPago
import java.text.NumberFormat
import java.util.Locale


@Composable
fun ItemInscripcion(data: Inscripcion) {
    val bool = remember { mutableStateOf(false) }
    val importe = NumberFormat.getCurrencyInstance(Locale("es", "PE")).format(data.precio)
    ItemPago(label = "Libro", text = data.inscripcion.toString(), ic = Icons.Default.Book)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "NH: ${data.codinscripcion}", fontSize = 12.sp)
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.Money, contentDescription = null)
        Text(text = "Importe $importe", fontSize = 12.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "Inscribir?", fontSize = 12.sp)
        Switch(checked = bool.value, onCheckedChange = { bool.value = it })
    }
}