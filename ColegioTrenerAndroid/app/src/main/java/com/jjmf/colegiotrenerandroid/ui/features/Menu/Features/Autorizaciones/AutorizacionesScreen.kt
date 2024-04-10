package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Switch
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.domain.model.EstadoAutorizacion
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.components.CardAutorizacionEstado
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.components.SwitchAutorizacion
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1

@Composable
fun AutorizacionesScreen(
    viewModel: AutorizacionesViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, ColorT1, RoundedCornerShape(15.dp))
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Estado",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(
                    selected = viewModel.estado == Estado.Activo,
                    onClick = {
                        viewModel.estado = Estado.Activo
                        viewModel.listarAutorizaciones(Estado.Activo)
                    }
                )
                Text(
                    text = "Activos",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(
                    selected = viewModel.estado == Estado.Vencido,
                    onClick = {
                        viewModel.estado = Estado.Vencido
                        viewModel.listarAutorizaciones(Estado.Vencido)
                    }
                )
                Text(
                    text = "Vencidos",
                    fontSize = 14.sp
                )
            }

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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (viewModel.autorizacion == it) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(ColorP1)
                                    .clickable {
                                        viewModel.autorizacion = it
                                        viewModel.listarEstados(it.idautorizacion.toString())
                                    }
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .border(2.dp, ColorP1, CircleShape)
                                    .clickable {
                                        viewModel.autorizacion = it
                                        viewModel.listarEstados(it.idautorizacion.toString())
                                    }
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f),
                            text = it.autorizacion.toString(),
                            fontSize = 12.sp,
                            lineHeight = 14.sp
                        )
                        IconButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.linkPdf))
                                context.startActivity(intent)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = null,
                                tint = ColorP1
                            )
                        }
                    }
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
                            CardAutorizacionEstado(estado = estado)
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


enum class Estado(val code: String) {
    Activo("A"),
    Vencido("V")
}