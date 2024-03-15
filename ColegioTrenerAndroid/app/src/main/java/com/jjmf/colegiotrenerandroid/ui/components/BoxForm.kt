package com.jjmf.colegiotrenerandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoxForm(
    modifier: Modifier = Modifier,
    value: String,
    newValue: (String) -> Unit,
    icon: ImageVector,
    label: String,
    isEnabled: Boolean = true,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(30.dp)
        )

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {

            if (value.isEmpty()) {
                Text(
                    text = label,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Medium
                )
            }

            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = newValue,
                keyboardOptions = KeyboardOptions(
                    capitalization = capitalization,
                    keyboardType = keyboardType
                ),
                singleLine = true,
                enabled = isEnabled
            )
        }

        if (!isEnabled) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun BoxFormField(
    value: TextFieldValue,
    newValue: (TextFieldValue) -> Unit,
    icon: ImageVector,
    label: String,
    isEnabled: Boolean = true,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(30.dp)
        )

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {

            if (value.text.isEmpty()) {
                Text(
                    text = label,
                    fontSize = 18.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Medium
                )
            }

            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = newValue,
                keyboardOptions = KeyboardOptions(
                    capitalization = capitalization,
                    keyboardType = keyboardType
                ),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 18.sp
                ),
                enabled = isEnabled
            )
        }

        if (!isEnabled) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
fun BoxFormSelect(
    modifier: Modifier = Modifier,
    value: String,
    newValue: (String) -> Unit,
    icon: ImageVector,
    label: String,
    click:()->Unit,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { click() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(25.dp)
        )

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {

            if (value.isEmpty()) {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Medium
                )
            }

            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = newValue,
                keyboardOptions = KeyboardOptions(
                    capitalization = capitalization,
                    keyboardType = keyboardType
                ),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp
                ),
                readOnly = true,
                enabled = false
            )
        }

        Icon(
            imageVector = Icons.Outlined.ArrowDropDown,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(25.dp)
        )
    }
}