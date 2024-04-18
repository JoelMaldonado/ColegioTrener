package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jjmf.colegiotrenerandroid.ui.common.toSoles
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.PagosViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPago
import com.jjmf.colegiotrenerandroid.util.format
import java.util.Date

@Composable
fun PagosVencidosScreen(
    viewModel: PagosViewModel
) {



    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(viewModel.listPagosVencidos) {
                CardPago(
                    title = it.concepto.toString().trim()
                ) {
                    ItemPago(label = "Deuda:", text = it.saldo.toSoles())
                    ItemPago(label = "Fec. Ven:", text = it.fecven.format())
                }
            }
        }

        if (viewModel.isLoadingVencidos) {
            CircularProgressIndicator()
        }

    }


}