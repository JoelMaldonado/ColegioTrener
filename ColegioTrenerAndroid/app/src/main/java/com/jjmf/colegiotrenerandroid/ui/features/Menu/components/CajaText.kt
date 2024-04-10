package com.jjmf.colegiotrenerandroid.ui.features.Menu.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.ui.theme.ColorFondo
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1

@Composable
fun CajaText(
    modifier: Modifier,
    value: String,
    newValue: (String) -> Unit,
    label: String,
    isEnabled: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = newValue,
        label = {
            Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, maxLines = 1)
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedBorderColor = ColorT1,
            focusedBorderColor = ColorT1,
            focusedLabelColor = ColorT1,
            unfocusedSuffixColor = Color.Black,
            disabledContainerColor = Color.White,
            disabledBorderColor = ColorT1
        ),
        trailingIcon = if (isEnabled) null
        else {
            {
                Icon(imageVector = Icons.Outlined.Lock, contentDescription = null)
            }
        },
        textStyle = TextStyle(fontSize = 14.sp),
        singleLine = true,
        enabled = isEnabled,
        maxLines = 1
    )
}