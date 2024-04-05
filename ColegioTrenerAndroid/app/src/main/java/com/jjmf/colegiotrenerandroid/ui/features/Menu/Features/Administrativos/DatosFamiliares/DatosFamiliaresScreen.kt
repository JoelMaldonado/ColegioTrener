package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.colegiotrenerandroid.R
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.components.ItemDatoFamiliar
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.ApoderadoDatosAdmin.ApoderadoDatosAdminScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.ClubesDatosAdmin.ClubesDatosAdminScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.HijosDatosAdmin.HijosDatosAdminScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.PadresDatosAdmin.PadresDatosAdminScreen
import com.jjmf.colegiotrenerandroid.ui.navigation.Rutas
import com.jjmf.colegiotrenerandroid.ui.navigation.navigateTo
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.util.enums.TipoFamiliar

@Composable
fun DatosFamiliaresScreen(
    back: () -> Unit,
    viewModel: DatosFamiliaresViewModel = hiltViewModel()
) {

    BackHandler(onBack = back)

    val navDatosAdmin = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorP1.copy(0.8f))
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            ItemDatoFamiliar(
                modifier = Modifier.weight(1f),
                img = R.drawable.img_padres,
                texto = stringResource(id = R.string.padres),
                click = {
                    viewModel.tab = TipoFamiliar.Padre
                    navDatosAdmin.navigateTo(Rutas.Administrativos.Datos.Padres)
                },
                isSelected = viewModel.tab == TipoFamiliar.Padre
            )

            ItemDatoFamiliar(
                modifier = Modifier.weight(1f),
                img = R.drawable.img_apoderado,
                texto = stringResource(id = R.string.apoderado),
                click = {
                    viewModel.tab = TipoFamiliar.Apoderado
                    navDatosAdmin.navigateTo(Rutas.Administrativos.Datos.Apoderado)
                },
                isSelected = viewModel.tab == TipoFamiliar.Apoderado
            )

            ItemDatoFamiliar(
                modifier = Modifier.weight(1f),
                img = R.drawable.img_hijos,
                texto = stringResource(id = R.string.hijos),
                click = {
                    viewModel.tab = TipoFamiliar.Hijo
                    navDatosAdmin.navigateTo(Rutas.Administrativos.Datos.Hijos)
                },
                isSelected = viewModel.tab == TipoFamiliar.Hijo
            )

            ItemDatoFamiliar(
                modifier = Modifier.weight(1f),
                img = R.drawable.img_clubs,
                texto = stringResource(id = R.string.clubes),
                click = {
                    viewModel.tab = TipoFamiliar.Club
                    navDatosAdmin.navigateTo(Rutas.Administrativos.Datos.Clubes)
                },
                isSelected = viewModel.tab == TipoFamiliar.Club
            )

        }

        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navDatosAdmin,
            startDestination = Rutas.Administrativos.Datos.Padres.route
        ) {
            composable(
                route = Rutas.Administrativos.Datos.Padres.route
            ) {
                PadresDatosAdminScreen(
                    back = back
                )
            }

            composable(
                route = Rutas.Administrativos.Datos.Apoderado.route
            ) {
                ApoderadoDatosAdminScreen(
                    back = back
                )
            }

            composable(
                route = Rutas.Administrativos.Datos.Hijos.route
            ) {
                HijosDatosAdminScreen(
                    back = back
                )
            }

            composable(
                route = Rutas.Administrativos.Datos.Clubes.route
            ) {
                ClubesDatosAdminScreen(
                    back = back
                )
            }

        }
    }
}
