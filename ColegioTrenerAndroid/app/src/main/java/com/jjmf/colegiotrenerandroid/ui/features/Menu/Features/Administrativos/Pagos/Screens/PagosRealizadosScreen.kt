package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.PagosViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPago
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.util.format
import java.util.Calendar
import java.util.Date

@Composable
fun PagosRealizadosScreen(
    viewModel: PagosViewModel
) {


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                IconButton(
                    onClick = {
                        viewModel.yearBefore()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }

                Text(
                    text = viewModel.year.format("yyyy"),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = ColorP1
                )

                IconButton(
                    onClick = {
                        viewModel.yearAfter()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = ColorP1
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.listPagosRealizados) {
                    CardPago(
                        title = "${it.concepto}",
                        label = "Nro. pago: 01"
                    ) {
                        ItemPago(
                            label = "Doc:",
                            text = "D/I ${it.numdoc}"
                        )
                        ItemPago(
                            label = "Grado/Lugar Pago:",
                            text = "${it.tipdoc}"
                        )
                        ItemPago(
                            label = "Fec. Movimiento:",
                            text = it.fecpag.format()
                        )
                        HorizontalDivider()
                        ItemPago(
                            ic = Icons.Default.MonetizationOn,
                            label = "Importe:",
                            text = "S/${it.importepagado}"
                        )
                        ItemPago(ic = Icons.Default.AccessTime, label = "Mora:", text = "S/${it.mora}")
                        ItemPago(
                            ic = Icons.Default.CalendarMonth,
                            label = "Fec. Penalidad:",
                            text = it.fecven.format()
                        )
                    }
                }
            }

        }

        if (viewModel.isLoadingRealizados){
            CircularProgressIndicator()
        }
    }

}