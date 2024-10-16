package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.CitaInforme
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.util.show


@Composable
fun ItemPago(
    ic: ImageVector? = null,
    label: String,
    text: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        ic?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .size(14.dp)
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        Text(
            text = text,
            textAlign = TextAlign.End,
            fontSize = 12.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ItemPagoExtend(
    ic: ImageVector? = null,
    label: String,
    text: String,
    citaInforme: CitaInforme,
    onClick: (CitaInforme) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            ic?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(14.dp)
                )
            }
            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Text(
                text = text,
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
        }

        if (!citaInforme.linkInforme.equals("")) {
            Button(
                onClick = {
                    onClick(citaInforme)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorS1
                ),
                modifier = Modifier.height(32.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = "Descargar")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Informe",
                    fontSize = 16.sp
                )
            }
        }
    }
}