package com.jjmf.colegiotrenerandroid.ui.features.Menu.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.DatosFamiliaresScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones.InscripcionesScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.PagosScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.MenuViewModel
import com.jjmf.colegiotrenerandroid.ui.navigation.Rutas

fun NavGraphBuilder.administrativosRoutes(
    viewModel: MenuViewModel,
    back: () -> Unit
) {
    this.apply {

        composable(
            route = Rutas.Administrativos.Datos.route
        ) {
            viewModel.title = "Datos"

            DatosFamiliaresScreen(
                back = back
            )
        }

        composable(Rutas.Administrativos.Pagos.route) {
            viewModel.title = "Pagos"
            PagosScreen(
                back = back
            )
        }

        composable(
            route = Rutas.Administrativos.Inscripciones.route
        ) {
            viewModel.title = "Inscripciones"
            InscripcionesScreen()
        }
    }
}