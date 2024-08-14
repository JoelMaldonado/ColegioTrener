package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones.components.CardInscripcion
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.util.show

@Composable
fun InscripcionesScreen(
    back: () -> Unit,
    viewModel: InscripcionesViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    if (viewModel.alert) {
        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
            titleText = "Warning!"
            contentText = "No se encuentra activo la inscripcion de talleres"
            setConfirmButton("OK") {
                viewModel.alert = false
                back()
                dismissWithAnimation()
            }
            setCancelable(false)
            show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        SelectHijo(
            click = {
                viewModel.ctacli = it
                viewModel.getListInscripciones(it)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when {
                viewModel.isLoading -> CircularProgressIndicator(modifier = Modifier.padding(top = 32.dp))
                viewModel.listInscripcion.isEmpty() -> Text(
                    text = "Sin inscripciones",
                    color = ColorT1,
                    modifier = Modifier.padding(top = 32.dp)
                )

                viewModel.listInscripcion.isNotEmpty() -> {
                    viewModel.listInscripcion
                        .groupBy { it.tipoinscripcion }
                        .entries
                        .forEach {
                            CardInscripcion(
                                title = it.key.toString(),
                                data = it.value,
                                ctacli = viewModel.ctacli ?: return@forEach
                            )
                        }
                }
            }
        }


    }
}
