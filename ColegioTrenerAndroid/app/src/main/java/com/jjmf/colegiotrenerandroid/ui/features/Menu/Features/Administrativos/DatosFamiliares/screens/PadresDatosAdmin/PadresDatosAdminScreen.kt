package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.PadresDatosAdmin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.outlined.Phonelink
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material.icons.outlined.Warehouse
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.R
import com.jjmf.colegiotrenerandroid.ui.components.BoxForm
import com.jjmf.colegiotrenerandroid.util.enums.TipoFamiliar
import com.jjmf.colegiotrenerandroid.util.format

@Composable
fun PadresDatosAdminScreen(
    back: () -> Unit,
    viewModel: PadresDatosAdminViewModel = hiltViewModel()
) {
    BackHandler(onBack = back)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilterChip(
                selected = viewModel.tab == TipoFamiliar.Padre,
                onClick = {
                    viewModel.tab = TipoFamiliar.Padre
                    viewModel.setearDatos(viewModel.padre)
                },
                label = { Text(text = stringResource(id = R.string.padre)) }
            )
            FilterChip(
                selected = viewModel.tab == TipoFamiliar.Madre,
                onClick = {
                    viewModel.tab = TipoFamiliar.Madre
                    viewModel.setearDatos(viewModel.madre)
                },
                label = { Text(text = stringResource(id = R.string.madre)) }
            )
        }

        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                BoxForm(
                    value = viewModel.nombre,
                    newValue = { viewModel.nombre = it },
                    icon = Icons.Outlined.Person,
                    label = "Nombres",
                    isEnabled = false
                )

                BoxForm(
                    value = viewModel.apodo,
                    newValue = { viewModel.apodo = it },
                    icon = Icons.Outlined.Person,
                    label = "Nombre que usa",
                    isEnabled = false
                )

                BoxForm(
                    value = viewModel.tipoDoc,
                    newValue = { viewModel.tipoDoc = it },
                    icon = Icons.Outlined.Wallet,
                    label = "Tipo de Documento",
                    isEnabled = false
                )

                BoxForm(
                    value = viewModel.numDoc,
                    newValue = { viewModel.numDoc = it },
                    icon = Icons.Outlined.Badge,
                    label = "Número de Documento"
                )

                BoxForm(
                    value = viewModel.fechaNac?.format() ?: "",
                    newValue = {

                    },
                    icon = Icons.Outlined.CalendarMonth,
                    label = "Fecha de Nacimiento"
                )

                BoxForm(
                    value = viewModel.distrito,
                    newValue = { viewModel.distrito = it },
                    icon = Icons.Outlined.LocationCity,
                    label = "Distrito"
                )

                BoxForm(
                    value = viewModel.direc,
                    newValue = { viewModel.direc = it },
                    icon = Icons.Outlined.LocationOn,
                    label = "Dirección"
                )

                BoxForm(
                    value = viewModel.cel,
                    newValue = { viewModel.cel = it },
                    icon = Icons.Outlined.PhoneAndroid,
                    label = "Celular"
                )

                BoxForm(
                    value = viewModel.telf,
                    newValue = { viewModel.telf = it },
                    icon = Icons.Outlined.Phone,
                    label = "Teléfono"
                )

                BoxForm(
                    value = viewModel.empresa,
                    newValue = { viewModel.empresa = it },
                    icon = Icons.Outlined.Warehouse,
                    label = "Empresa"
                )

                BoxForm(
                    value = viewModel.cargo,
                    newValue = { viewModel.nombre = it },
                    icon = Icons.Outlined.Work,
                    label = "Cargo/Área"
                )

                BoxForm(
                    value = viewModel.telfEmpresa,
                    newValue = { viewModel.telfEmpresa = it },
                    icon = Icons.Outlined.Phonelink,
                    label = "Telefono de Empresa"
                )

                BoxForm(
                    value = viewModel.correo,
                    newValue = { viewModel.correo = it },
                    icon = Icons.Outlined.Mail,
                    label = "Correo"
                )

                Button(onClick = {}) {

                    Text(text = "Grabar")
                }
            }
        }

    }
}