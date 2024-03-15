package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1


@Composable
fun ItemDatoFamiliar(
    modifier: Modifier,
    @DrawableRes img:Int,
    texto:String,
    click: () -> Unit,
    isSelected: Boolean,
    scale: ContentScale = ContentScale.FillHeight
) {
    Column(
        modifier = modifier
            .clickable {
                click()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier.height(70.dp),
            contentScale = scale
        )
        Text(text = texto)
        AnimatedVisibility(isSelected) {
            HorizontalDivider(
                color = ColorS1,
                thickness = 3.dp
            )
        }
    }
}