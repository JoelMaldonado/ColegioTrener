package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones.components.ItemInscripcion
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.util.show

@Composable
fun InscripcionesScreen(
    viewModel: InscripcionesViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        SelectHijo(
            click = {
                viewModel.getListInscripciones(it)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            viewModel.listInscripcion.groupBy { it.tipoinscripcion }.entries.forEach {
                CardPago(
                    title = it.key.toString()
                ) {
                    it.value.forEach {
                        ItemInscripcion(it)
                    }
                }
            }
        }


    }
}
