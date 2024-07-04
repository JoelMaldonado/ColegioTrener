package com.jjmf.colegiotrenerandroid.ui.features.Notificaciones

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Notificaciones.components.ItemNotificacion
import com.jjmf.colegiotrenerandroid.ui.theme.ColorFondo

@Composable
fun NotificacionesScreen(
    viewModel: NotificacionesViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
    ) {
        SelectHijo(
            click = { ctli ->
                viewModel.getNotificaciones(
                    ctacli = ctli
                )
            }
        )

        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return
        }

        if (viewModel.list.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No hay notificaciones",
                    color = Color.Gray
                )
            }
            return
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.list) { notif ->
                ItemNotificacion(
                    notificacion = notif
                )
            }
        }
    }
}
