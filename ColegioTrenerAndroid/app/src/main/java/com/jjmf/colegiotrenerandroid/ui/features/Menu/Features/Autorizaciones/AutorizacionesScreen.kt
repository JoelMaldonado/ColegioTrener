package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.snap
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1

@Composable
fun AutorizacionesScreen(
    viewModel: AutorizacionesViewModel = hiltViewModel()
) {

    val select = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, ColorT1, RoundedCornerShape(15.dp))
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Estado",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(selected = true, onClick = {})
                Text(
                    text = "Activos",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(selected = false, onClick = {})
                Text(
                    text = "Vencidos",
                    fontSize = 14.sp
                )
            }

            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = "Autorizacion",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, ColorT1, RoundedCornerShape(15.dp))
                    .padding(horizontal = 8.dp)
            ) {
                viewModel.list.forEach {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (select.value == it.autorizacion) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(ColorP1)
                                    .clickable {
                                        select.value = it.autorizacion.toString()
                                    }
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .border(2.dp, ColorP1, CircleShape)
                                    .clickable {
                                        select.value = it.autorizacion.toString()
                                    }
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f),
                            text = it.autorizacion.toString(),
                            fontSize = 12.sp,
                            lineHeight = 14.sp
                        )
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = null,
                                tint = ColorP1
                            )
                        }
                    }
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                viewModel.listHijos.forEach {
                    CardPago(title = it.nombre.toString()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = ColorP1
                            )
                            Text(
                                text = "Codigo: ",
                                color = ColorP1,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(text = it.ctacli.toString(), fontSize = 12.sp)

                            Spacer(modifier = Modifier.weight(1f))

                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Default.DoorFront,
                                contentDescription = null,
                                tint = ColorP1
                            )
                            Text(
                                text = "Clase: ",
                                color = ColorP1,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(text = it.param1.toString(), fontSize = 12.sp)

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = "Autorizo?",
                                fontSize = 12.sp,
                                color = ColorP1,
                                fontWeight = FontWeight.SemiBold
                            )

                            Switch(checked = true, onCheckedChange = {})

                        }
                    }
                }

            }

        }

        viewModel.error?.let {
            Snackbar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                action = {
                    IconButton(
                        onClick = {
                            viewModel.setError(false)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                containerColor = ColorP1,
                contentColor = Color.White
            ) {
                Text(it)
            }
            viewModel.setError(delay = true)

        }

    }

}