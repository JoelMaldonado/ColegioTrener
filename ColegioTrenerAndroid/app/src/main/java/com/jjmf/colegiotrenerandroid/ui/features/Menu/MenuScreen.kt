package com.jjmf.colegiotrenerandroid.ui.features.Menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.colegiotrenerandroid.R
import com.jjmf.colegiotrenerandroid.ui.components.TopBar
import com.jjmf.colegiotrenerandroid.ui.features.Inicio.InicioScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.DiariaAcumulada.DiariaAcumuladaScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Autorizaciones.AutorizacionesScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.CitaInforme.CitaInformeScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Incumplimientos.TareasIncumplimientosScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Pendientes.TareasPendientesScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.routes.administrativosRoutes
import com.jjmf.colegiotrenerandroid.ui.navigation.Rutas

@Composable
fun MenuScreen(
    viewModel: MenuViewModel = hiltViewModel()
) {


    val navMenu = rememberNavController()

    Image(
        painter = painterResource(id = R.drawable.fondo_menu),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopBar(
            title = viewModel.title
        )

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navMenu,
            startDestination = Rutas.Inicio.route
        ) {

            composable(
                route = Rutas.Inicio.route
            ) {
                viewModel.title = "Inicio"
                InicioScreen(navMenu)
            }

            administrativosRoutes(
                viewModel = viewModel,
                back = {
                    navMenu.popBackStack()
                }
            )

            /**
             * Asistencia
             */
            composable(
                route = Rutas.Asistencia.DiariaAcumulada.route
            ) {
                viewModel.title = "Información Diaria y Acumulada"
                DiariaAcumuladaScreen()
            }

            /**
             * Tareas
             */
            composable(
                route = Rutas.Tareas.Pendientes.route
            ) {
                viewModel.title = "Calendario de Tareas Pendientes"
                TareasPendientesScreen()
            }
            composable(
                route = Rutas.Tareas.Incumplimientos.route
            ) {
                viewModel.title = "Información de Incumplimientos"
                TareasIncumplimientosScreen()
            }

            /**
             * Resultados
             */
            composable(
                route = Rutas.Resultados.CitaInforme.route
            ) {
                viewModel.title = "Citas para Entrega de Informes"
                CitaInformeScreen()
            }

            /**
             * Autorizaciones
             */
            composable(
                route = Rutas.Autorizaciones.route
            ) {
                viewModel.title = "Autorizaciones"
                AutorizacionesScreen()
            }

        }
    }

}