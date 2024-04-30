package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jjmf.colegiotrenerandroid.data.services.request.DataClubRequest
import com.jjmf.colegiotrenerandroid.domain.model.Club
import com.jjmf.colegiotrenerandroid.ui.components.BoxForm
import com.jjmf.colegiotrenerandroid.ui.components.BoxFormSelect
import com.jjmf.colegiotrenerandroid.ui.theme.ColorFondo
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.util.show

@Composable
fun DialogAddClub(
    close: () -> Unit,
    add: (DataClubRequest) -> Unit,
    list: List<Club>
) {

    val context = LocalContext.current

    val club = remember { mutableStateOf<Club?>(null) }
    val vinculo = remember { mutableStateOf("") }
    val numCarnet = remember { mutableStateOf("") }

    val isExpanded = remember { mutableStateOf(false) }
    val isExpanded2 = remember { mutableStateOf(false) }

    Dialog(onDismissRequest = close) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .background(ColorFondo)
        ) {
            Text(
                text = "Agregar Club",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorT1)
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                BoxFormSelect(
                    value = club.value?.descrip ?: "Seleccionar Club",
                    newValue = {},
                    icon = Icons.Default.AllInclusive,
                    label = "Club",
                    capitalization = KeyboardCapitalization.Words,
                    click = {
                        isExpanded.value = !isExpanded.value
                    }
                )

                DropdownMenu(expanded = isExpanded.value, onDismissRequest = {
                    isExpanded.value = false
                }) {
                    list.forEach {
                        DropdownMenuItem(
                            modifier = Modifier.fillMaxWidth(),
                            text = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "${it.descrip}"
                                )
                            },
                            onClick = {
                                club.value = it
                                isExpanded.value = false
                            })
                    }
                }

                BoxForm(
                    value = numCarnet.value,
                    newValue = { numCarnet.value = it },
                    icon = Icons.Default.Badge,
                    label = "Nro. Carnet",
                    keyboardType = KeyboardType.Number
                )

                Box {

                    BoxFormSelect(
                        value = vinculo.value,
                        newValue = {},
                        icon = Icons.Default.AllInclusive,
                        label = "Vinculo",
                        capitalization = KeyboardCapitalization.Words,
                        click = {
                            isExpanded2.value = !isExpanded2.value
                        }
                    )

                    DropdownMenu(
                        expanded = isExpanded2.value,
                        onDismissRequest = {
                            isExpanded.value = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Padre")
                            },
                            onClick = {
                                vinculo.value = "Padre"
                                isExpanded2.value = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Madre")
                            },
                            onClick = {
                                vinculo.value = "Madre"
                                isExpanded2.value = false
                            }
                        )
                    }
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {

                            if (club.value == null) {
                                context.show("Club vacio")
                                return@Button
                            }

                            if (numCarnet.value.isEmpty()) {
                                context.show("Carnet vacio")
                                return@Button
                            }

                            close()

                            val body = DataClubRequest(
                                accion = "Crear",
                                codClub = club.value?.codigo.toString(),
                                codParentesco = if (vinculo.value == "Padre") "001" else "002",
                                nroCarnet = numCarnet.value
                            )

                            add(body)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorP1
                        )
                    ) {
                        Text(text = "Agregar")
                    }
                    Button(
                        onClick = close,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorS1
                        )
                    ) {
                        Text(text = "Cancelar")
                    }
                }
            }
        }
    }
}