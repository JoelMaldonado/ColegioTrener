package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.Autorizacion
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1


@Composable
fun ItemAutorizacion(
    selected: Autorizacion?,
    autorizacion: Autorizacion,
    click:()->Unit
) {

    val context= LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (selected == autorizacion) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(ColorP1)
                    .clickable {
                        click()
                    }
            )
        } else {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .border(2.dp, ColorP1, CircleShape)
                    .clickable {
                        click()
                    }
            )
        }

        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f),
            text = autorizacion.autorizacion.toString(),
            fontSize = 12.sp,
            lineHeight = 14.sp
        )
        IconButton(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(autorizacion.linkPdf))
                context.startActivity(intent)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Visibility,
                contentDescription = null,
                tint = ColorP1
            )
        }
    }
}
