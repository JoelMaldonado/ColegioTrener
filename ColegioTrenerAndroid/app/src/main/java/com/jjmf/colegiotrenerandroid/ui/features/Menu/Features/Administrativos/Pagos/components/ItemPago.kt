package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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


@Composable
fun ItemPago(
    ic: ImageVector? = null,
    label: String,
    text: String
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
                    .size(20.dp)
            )
        }
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray
        )
        Text(
            text = text,
            textAlign = TextAlign.End,
            fontSize = 14.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold
        )
    }
}