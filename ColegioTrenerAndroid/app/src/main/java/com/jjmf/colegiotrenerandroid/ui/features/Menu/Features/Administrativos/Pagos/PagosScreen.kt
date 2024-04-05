package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.Screens.PagosPendientesScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.Screens.PagosRealizadosScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.Screens.PagosVencidosScreen
import com.jjmf.colegiotrenerandroid.ui.theme.ColorFondo
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.util.show

@Composable
fun PagosScreen(
    back: () -> Unit,
    viewModel: PagosViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    val tab = remember { mutableStateOf(TabPagos.Pendientes) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
    ) {


        SelectHijo(
            click = {
                viewModel.ctacli = it
                viewModel.getPagosPendientes(it)
                viewModel.getPagosVencidos(it)
                viewModel.getPagosRealizados(it)
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TabPagos.entries.forEach {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .border(0.5.dp, color = Color.Black)
                        .background(if (it == tab.value) ColorS1 else Color.White)
                        .clickable {
                            tab.value = it
                        }
                        .padding(vertical = 4.dp),
                    text = it.name,
                    textAlign = TextAlign.Center,
                    color = if (it == tab.value) Color.White else Color.Black
                )
            }
        }

        when (tab.value) {
            TabPagos.Pendientes -> PagosPendientesScreen(viewModel)
            TabPagos.Vencidas -> PagosVencidosScreen(viewModel)
            TabPagos.Realizados -> PagosRealizadosScreen(viewModel)
        }

    }


}

enum class TabPagos(val code: Int) {
    Pendientes(0),
    Vencidas(1),
    Realizados(2)
}