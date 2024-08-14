package com.jjmf.colegiotrenerandroid.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun DescargarImagenScreen(
    viewModel: DescargarImagenViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ){
        AsyncImage(model = viewModel.url, contentDescription = "Perro")
        Button(
            onClick = {
                viewModel.descargarImagen(context)
            }
        ) {
            Text(text = "Descargar Imagen")
        }
        Button(
                onClick = {
                    viewModel.descargarPDF(context)
                }
                ) {
            Text(text = "Descargar Pdf")
        }
    }
}