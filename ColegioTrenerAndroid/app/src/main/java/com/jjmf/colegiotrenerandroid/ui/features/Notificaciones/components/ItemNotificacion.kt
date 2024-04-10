package com.jjmf.colegiotrenerandroid.ui.features.Notificaciones.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1


@Composable
fun ItemNotificacion() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Inscripción de libros", fontSize = 14.sp)
            Text(text = "Quedan 3 días para el cierre de inscripciones", fontSize = 12.sp)
        }
        FloatingActionButton(
            modifier = Modifier.size(40.dp),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
                context.startActivity(intent)
            },
            shape = CircleShape,
            containerColor = ColorS1,
            contentColor = Color.White
        ) {
            Text(text = "IR")
        }
    }
    HorizontalDivider()
}
