package com.jjmf.colegiotrenerandroid.ui.features.Inicio.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextInicio(text:String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )

        HorizontalDivider(
            color = Color.White,
            modifier = Modifier.padding(bottom = 15.dp)
        )
    }
}