package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.components.CardAutorizacionEstado
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.components.ItemAutorizacion
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.components.SelectEstadoAutorizacion
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1

@Composable
fun AutorizacionesScreen(
    viewModel: AutorizacionesViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            SelectEstadoAutorizacion(
                estado = viewModel.estado,
                click = {
                    viewModel.estado = it
                    viewModel.listarAutorizaciones(it)
                }
            )

            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = "Autorización",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, ColorT1, RoundedCornerShape(15.dp))
                    .padding(horizontal = 8.dp)
            ) {
                viewModel.list.forEach {
                    ItemAutorizacion(
                        selected = viewModel.autorizacion,
                        autorizacion = it,
                        click = {
                            viewModel.autorizacion = it
                            viewModel.listarEstados(it.idautorizacion.toString())
                        }
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!viewModel.isLoadingEstados) {
                    if (viewModel.listEstados.isNotEmpty()) {
                        viewModel.listEstados.forEach { estado ->
                            CardAutorizacionEstado(
                                tipoEstado = viewModel.estado,
                                estado = estado,
                                click = {
                                    viewModel.grabar(
                                        idPermiso = viewModel.autorizacion?.idautorizacion.toString(),
                                        ctacli = estado.ctacli.toString(),
                                        autorizo = if (it) "1" else "0"
                                    )
                                }
                            )
                        }
                    } else {
                        Text(
                            modifier = Modifier.padding(top = 30.dp),
                            text = "Sin datos",
                            color = Color.Gray
                        )
                    }
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(top = 30.dp)
                    )
                }
            }

        }

        viewModel.error?.let {
            Snackbar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                action = {
                    IconButton(
                        onClick = {
                            viewModel.setError(false)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                containerColor = ColorP1,
                contentColor = Color.White
            ) {
                Text(it)
            }
            viewModel.setError(delay = true)
        }

    }

}
