package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.ClubesDatosAdmin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.components.dialogs.DialogAddClub
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1

@Composable
fun ClubesDatosAdminScreen(
    back: () -> Unit,
    viewModel: ClubesDatosAdminViewModel = hiltViewModel()
) {


    if (viewModel.alertAddClub) {
        DialogAddClub(
            close = {
                viewModel.alertAddClub = false
            },
            add = {
                viewModel.addClub(it)
            },
            list = viewModel.listClubs
        )
    }

    BackHandler(onBack = back)

    LaunchedEffect(key1 = Unit) {
        viewModel.getDataClubs()
        viewModel.getClubs()
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
                text = "*Clubes a los que pertenece",
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
                        viewModel.alertAddClub = true
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
            items(viewModel.listDataClubs) {
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
                            color = ColorS1,
                            thickness = 12.dp
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .background(ColorT1.copy(0.7f))
                                .padding(horizontal = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "${it.vinculo}",
                                textAlign = TextAlign.Center
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp)
                        ) {
                            Text(text = "Club: ${it.club}")
                            Text(text = "Nro Carné: ${it.nrocar}")
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