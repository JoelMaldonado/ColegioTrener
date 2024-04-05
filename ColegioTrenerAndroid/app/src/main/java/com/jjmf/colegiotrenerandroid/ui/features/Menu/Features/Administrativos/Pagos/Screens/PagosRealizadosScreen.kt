package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.PagosViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPagoRealizado
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPago
import com.jjmf.colegiotrenerandroid.ui.theme.ColorFondo
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.util.format
import java.text.NumberFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun PagosRealizadosScreen(
    viewModel: PagosViewModel
) {


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(30.dp)
                    .background(Brush.horizontalGradient(listOf(ColorFondo, ColorS1, ColorFondo))),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    14.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Text(
                    modifier = Modifier.clickable {
                        viewModel.yearBefore()
                    },
                    text = viewModel.year.minusYears(1).format("yyyy"),
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = viewModel.year.format("yyyy"),
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    modifier = Modifier.clickable {
                        viewModel.yearAfter()
                    },
                    text = viewModel.year.plusYears(1).format("yyyy"),
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.listPagosRealizados) {

                    val format = NumberFormat.getCurrencyInstance(Locale("es", "pe"))
                    val importe = format.format(it.importepagado)
                    val mora = format.format(it.mora)

                    CardPagoRealizado(
                        title = "${it.concepto}",
                        label = "Nro. pago: 01",
                        numDoc = it.numdoc.toString(),
                        medioPago = it.mediopago.toString(),
                        fecPago = it.fecpag.format(),
                        importe = importe,
                        mora = mora,
                        fecVen = it.fecven.format()
                    )
                }
            }

        }

        if (viewModel.isLoadingRealizados) {
            CircularProgressIndicator()
        }
    }

}