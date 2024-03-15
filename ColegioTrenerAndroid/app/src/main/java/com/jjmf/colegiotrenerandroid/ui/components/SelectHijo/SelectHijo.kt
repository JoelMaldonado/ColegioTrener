package com.jjmf.colegiotrenerandroid.ui.components.SelectHijo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.R
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1


@Composable
fun SelectHijo(
    click: (ctli: String)->Unit,
    viewModel: SelectHijoViewModel = hiltViewModel(),
) {

    val isVisible = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AnimatedVisibility(visible = !isVisible.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorT1),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                VerticalDivider(
                    modifier = Modifier.height(80.dp),
                    color = ColorS1,
                    thickness = 8.dp
                )

                Image(
                    modifier = Modifier.size(70.dp),
                    painter = painterResource(id = R.drawable.img_apoderado),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = viewModel.hijoSelected?.nombre ?: "Sin Seleccionar",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Badge,
                            contentDescription = null,
                            tint = ColorP1
                        )
                        Text(text = "Codigo: 00002528", color = Color.White, fontSize = 12.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = "Año: 2023", color = Color.White, fontSize = 12.sp)
                    }
                }

                IconButton(
                    onClick = {
                        isVisible.value = !isVisible.value
                    }
                ) {
                    val ic = if (isVisible.value) Icons.Default.ArrowDropUp
                    else Icons.Default.ArrowDropDown

                    Icon(
                        imageVector = ic,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        AnimatedVisibility(visible = isVisible.value) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                viewModel.listHijos.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(ColorT1)
                            .clickable {
                                viewModel.hijoSelected = it
                                click(it.ctacli.toString())
                                isVisible.value = false
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        AnimatedVisibility(visible = it.nombre == viewModel.hijoSelected?.nombre) {
                            VerticalDivider(
                                modifier = Modifier.height(80.dp),
                                color = ColorS1,
                                thickness = 8.dp
                            )
                        }

                        Image(
                            modifier = Modifier.size(70.dp),
                            painter = painterResource(id = R.drawable.img_apoderado),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "${it.nombre} ${it.apepat} ${it.apemat}",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Badge,
                                    contentDescription = null,
                                    tint = ColorP1
                                )
                                Text(
                                    text = "Codigo: ${it.ctacli}",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = "Año: ${it.anoaca}",
                                    color = Color.White,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}