package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.HijosDatosAdmin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.components.dialogs.DialogAddHijo
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.util.format

@Composable
fun HijosDatosAdminScreen(
    back: () -> Unit,
    viewModel: HijosDatosAdminViewModel = hiltViewModel()
) {

    if (viewModel.alertAddHijo) {
        DialogAddHijo(
            close = {
                viewModel.alertAddHijo = false
            },
            add = {
                viewModel.addHijo(it)
            }
        )
    }

    BackHandler(onBack = back)

    LaunchedEffect(key1 = Unit) {
        viewModel.getHijos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(ColorT1)
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "*Agregar el nombre de todos los hijos",
                color = Color.White,
                fontSize = 14.sp
            )

            Text(
                text = "Agregar",
                color = Color.White,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(ColorP1)
                    .clickable {
                        viewModel.alertAddHijo = true
                    }
                    .padding(
                        horizontal = 8.dp,
                        vertical = 3.dp
                    ),
                fontSize = 14.sp
            )

        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(viewModel.listHijos) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = ColorT1.copy(0.5f)
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(end = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        VerticalDivider(
                            color = ColorT1,
                            thickness = 12.dp
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 12.dp)
                        ) {
                            Text(text = it.nombre ?: "Sin Nombre")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CalendarMonth,
                                    contentDescription = null
                                )
                                Text(text = "Fec. Nacimiento: ${it.fechaNac.format()}", fontSize = 14.sp)
                            }
                        }
                        Icon(
                            imageVector = Icons.Sharp.Delete,
                            contentDescription = null,
                            tint = ColorS1
                        )

                    }

                }
            }
        }

    }
}