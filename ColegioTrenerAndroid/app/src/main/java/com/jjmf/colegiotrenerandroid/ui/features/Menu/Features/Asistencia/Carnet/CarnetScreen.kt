package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Carnet

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jjmf.colegiotrenerandroid.ui.components.SelectHijo.SelectHijo
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones.InscripcionesViewModel
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.components.CalendarioAsistencia
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.components.CardInasistencia
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.components.LeyendaAsistencia
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.components.CardAutorizacionEstado
import com.jjmf.colegiotrenerandroid.util.show

@Composable
fun CarnetScreen(
    viewModel: CarnetViewModel = hiltViewModel(),
) {

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {

        SelectHijo(
            click = {
                viewModel.ctacli = it
                viewModel.obtenerCarnet(it)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!viewModel.isLoadingEstados) {
                if (viewModel.list != null) {

                    Column(modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center) {
                            AsyncImage(model = ImageRequest.Builder(context)
                                .data(viewModel.list?.linkVista)
                                .crossfade(500)
                                .build(),
                                contentDescription = "carnet",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(200.dp))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                try{
                                    viewModel.list?.linkDescarga?.let {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.list?.linkDescarga))
                                        context.startActivity(intent)
                                    }?:run {
                                        context.show("Carnet no disponible")
                                    }
                                }catch (ex:Exception){
                                    context.show("No pudimos obtener el carnet solicitado")
                                }


                            }
                        ) {
                            Text(text = "Descargar Imagen")
                        }
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
}