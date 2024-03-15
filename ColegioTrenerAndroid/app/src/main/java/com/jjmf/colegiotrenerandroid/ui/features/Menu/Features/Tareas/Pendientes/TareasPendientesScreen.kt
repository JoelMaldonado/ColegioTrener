package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Pendientes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPago
import com.jjmf.colegiotrenerandroid.util.format
import java.util.Calendar

@Composable
fun TareasPendientesScreen(
    viewModel: TareasPendientesViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = viewModel::monthBefore) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = null
                    )
                }
                Text(text = viewModel.fecha.format("MMMM"))
                IconButton(onClick = viewModel::monthAfter) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.obtenerDiasDelMesConInicioEspecifico(Calendar.MONDAY).forEach { semana ->

                    Text(text = semana.format("dd"))
                }
            }
            /*
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Lun")
                                viewModel.getDias(Calendar.MONDAY).forEach {
                                    if (it == null) {
                                        Text(text = "")
                                    } else {
                                        Text(text = it.format("dd"))
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Mar")
                                viewModel.getDias(Calendar.TUESDAY).forEach {
                                    if (it == null) {
                                        Text(text = "")
                                    } else {
                                        Text(text = it.format("dd"))
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Mie")
                                viewModel.getDias(Calendar.WEDNESDAY).forEach {
                                    if (it == null) {
                                        Text(text = "")
                                    } else {
                                        Text(text = it.format("dd"))
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Jue")
                                viewModel.getDias(Calendar.THURSDAY).forEach {
                                    if (it == null) {
                                        Text(text = "")
                                    } else {
                                        Text(text = it.format("dd"))
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Vie")
                                viewModel.getDias(Calendar.FRIDAY).forEach {
                                    if (it == null) {
                                        Text(text = "")
                                    } else {
                                        Text(text = it.format("dd"))
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Sab")
                                viewModel.getDias(Calendar.SATURDAY).forEach {
                                    if (it == null) {
                                        Text(text = "")
                                    } else {
                                        Text(text = it.format("dd"))
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Dom")
                                viewModel.getDias(Calendar.SUNDAY).forEach {
                                    if (it == null) {
                                        Text(text = "")
                                    } else {
                                        Text(text = it.format("dd"))
                                    }
                                }
                            }
                        }

                        */
        }

        CardPago(
            title = "Total Acumulado",
            label = "Trimestre 3"
        ) {
            ItemPago(label = "Inasistencia", text = "0")
            ItemPago(label = "Tardanza", text = "4")
            ItemPago(label = "I. Justificada", text = "7")
            ItemPago(label = "I. Injustificada", text = "0")
        }


    }

}

