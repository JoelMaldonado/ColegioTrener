package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.domain.model.Justificacion
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPagoExtend
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion.components.AlertJustificacion
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion.components.CardJustificacion
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.util.show

@Composable
fun JustificacionScreen(
    viewModel: JustificacionViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var justificacion:Justificacion? by remember {
        mutableStateOf(null)
    }

    if (showDialog) {
        AlertJustificacion(
            showDialog = showDialog,
            dismissOnClickRefresh = {
                showDialog = false
                viewModel.getList(ctacli = viewModel.ctacli, fecha = viewModel.currentMonth.value)
            },
            dismissOnClick ={
                showDialog = false
            },
            viewModel,
            justificacion
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {

        SelectHijo(
            click = {
                viewModel.ctacli = it
                viewModel.getList(ctacli = it, fecha = viewModel.currentMonth.value)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (viewModel.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(top = 30.dp),
                    color = ColorP1
                )
            }

        } else {
            if (viewModel.list.isEmpty()) {
                Text(text = "Sin Resultados", color = Color.Gray)
            } else {
                viewModel.list?.let {
                    CardJustificacion(
                        data = it,
                        onClick = {
                            showDialog = true
                            justificacion = it
                        }
                    )
                }
            }
        }

    }
}