package com.jjmf.colegiotrenerandroid.ui.features.Login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1

@Composable
fun CajaLogin(
    value: String,
    newValue: (String) -> Unit,
    label: String,
    icon:ImageVector,
    isPass: Boolean = false
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(ColorP1),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(1.5.dp, ColorP1, RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty()) {
                Text(text = label, color = Color.Gray)
            }
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = newValue,
                singleLine = true,
                visualTransformation = if (isPass) PasswordVisualTransformation() else VisualTransformation.None
            )
        }
    }

}