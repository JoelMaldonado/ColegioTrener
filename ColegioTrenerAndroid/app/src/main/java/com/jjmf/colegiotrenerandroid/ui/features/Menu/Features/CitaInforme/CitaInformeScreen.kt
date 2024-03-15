package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.CitaInforme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.SelectYear
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPago

@Composable
fun CitaInformeScreen(
    viewModel: CitaInformeViewModel = hiltViewModel()
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SelectYear {
                viewModel.year = it
                viewModel.listarCitas()
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Trimestre.entries.forEach {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = viewModel.trimestre == it,
                            onClick = {
                                viewModel.trimestre = it
                                viewModel.listarCitas()
                            }
                        )
                        Text(text = it.nombre, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
        items(viewModel.list) {
            CardPago(
                title = "${it.nalumno}",
                modifier = Modifier.fillMaxWidth(),
                content = {
                    ItemPago(
                        label = "Fecha",
                        text = "${it.fechacita}",
                        ic = Icons.Default.CalendarMonth
                    )
                    ItemPago(
                        label = "Horario",
                        text = "${it.horario}",
                        ic = Icons.Default.AccessTime
                    )
                    ItemPago(
                        label = "Clase",
                        text = "${it.clase}",
                        ic = Icons.Default.DoorFront
                    )
                    ItemPago(
                        label = "Observación",
                        text = "${it.observa?.ifEmpty { "--" }}",
                        ic = Icons.Default.Visibility
                    )
                }
            )
        }
    }

}