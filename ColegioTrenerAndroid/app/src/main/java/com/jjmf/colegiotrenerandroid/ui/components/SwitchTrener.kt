package com.jjmf.colegiotrenerandroid.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1

@Composable
fun SwitchTrener(
    modifier: Modifier = Modifier,
    bool: Boolean,
    newValue: (Boolean) -> Unit
) {

    val off = animateDpAsState(
        targetValue = if (bool) 30.dp else 0.dp,
        tween(100),
        label = ""
    )
    val color =
        animateColorAsState(
            targetValue = if (bool) ColorP1 else ColorS1,
            tween(100),
            label = ""
        )

    Box(
        modifier = modifier
            .width(60.dp)
            .border(1.dp, Color.Gray)
    ) {

        Box(
            modifier = Modifier
                .width(30.dp)
                .offset(off.value)
                .background(color.value)
                .clickable{
                    newValue(!bool)
                },
            contentAlignment = Alignment.Center
        ) {
            val text = if (bool) "Si" else "No"
            Text(
                text = text,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                lineHeight = 16.sp
            )
        }

    }
}