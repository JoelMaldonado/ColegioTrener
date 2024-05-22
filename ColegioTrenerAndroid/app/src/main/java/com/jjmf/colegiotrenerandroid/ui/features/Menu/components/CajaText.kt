package com.jjmf.colegiotrenerandroid.ui.features.Menu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.domain.model.Distrito
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
@Composable
fun CajaTextField(
    modifier: Modifier,
    value: TextFieldValue,
    newValue: (TextFieldValue) -> Unit,
    label: String,
    isEnabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text
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
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}


@Composable
fun CajaSelectDistrito(
    modifier: Modifier,
    distrito: MutableState<Distrito?>,
    list: List<Distrito>
) {
    val bool = remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = distrito.value?.desdis ?: "Sin Definir",
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = {
                Text(
                    text = "Distrito",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedBorderColor = ColorT1,
                focusedBorderColor = ColorT1,
                focusedLabelColor = ColorT1,
                unfocusedSuffixColor = Color.Black
            ),
            trailingIcon = {
                val ic = if (bool.value) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown
                IconButton(onClick = { bool.value = true }) {
                    Icon(imageVector = ic, contentDescription = null)
                }
            }
        )
        DropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = bool.value,
            onDismissRequest = { bool.value = false }) {
            list.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.desdis ?: "Sin Definir") },
                    onClick = {
                        bool.value = false
                        distrito.value = it
                    }
                )
            }
        }
    }

}