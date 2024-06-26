package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.screens.ApoderadoDatosAdmin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.colegiotrenerandroid.R
import com.jjmf.colegiotrenerandroid.ui.components.BoxForm
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.components.dialogs.formatDateString
import com.jjmf.colegiotrenerandroid.ui.features.Menu.components.CajaSelectDistrito
import com.jjmf.colegiotrenerandroid.ui.features.Menu.components.CajaText
import com.jjmf.colegiotrenerandroid.ui.features.Menu.components.CajaTextField
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.util.enums.TipoFamiliar
import com.jjmf.colegiotrenerandroid.util.format
import com.jjmf.colegiotrenerandroid.util.show
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ApoderadoDatosAdminScreen(
    back: () -> Unit,
    viewModel: ApoderadoDatosAdminViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    BackHandler(onBack = back)

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CajaText(
                        modifier = Modifier.weight(3f),
                        value = viewModel.nombre,
                        newValue = { viewModel.nombre = it },
                        label = "Nombres",
                        isEnabled = false
                    )
                    CajaText(
                        modifier = Modifier.weight(2f),
                        value = viewModel.apodo,
                        newValue = { viewModel.apodo = it },
                        label = "Nombre que usa",
                        isEnabled = false
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CajaText(
                        modifier = Modifier.weight(1f),
                        value = viewModel.tipoDoc,
                        newValue = { viewModel.tipoDoc = it },
                        label = "Tipo de doc. de identidad",
                        isEnabled = false
                    )
                    CajaText(
                        modifier = Modifier.weight(1f),
                        value = viewModel.numDoc,
                        newValue = { viewModel.numDoc = it },
                        label = "Número de doc. de identidad",
                        isEnabled = false
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CajaTextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.fecha.value,
                        newValue = {
                            val formattedDate = formatDateString(it.text)
                            viewModel.fecha.value = TextFieldValue(
                                text = formattedDate,
                                selection = TextRange(formattedDate.length)
                            )
                        },
                        label = "Fecha de nacimiento",
                        keyboardType = KeyboardType.Number
                    )
                    CajaSelectDistrito(
                        modifier = Modifier.weight(1f),
                        distrito = viewModel.distrito,
                        list = viewModel.listDistritos,
                    )
                }

                CajaText(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.direc,
                    newValue = { viewModel.direc = it },
                    label = "Dirección"
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CajaText(
                        modifier = Modifier.weight(1f),
                        value = viewModel.cel,
                        newValue = { viewModel.cel = it },
                        label = "Celular"
                    )
                    CajaText(
                        modifier = Modifier.weight(1f),
                        value = viewModel.telf,
                        newValue = { viewModel.telf = it },
                        label = "Teléfono"
                    )
                }

                CajaText(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.empresa,
                    newValue = { viewModel.empresa = it },
                    label = "Empresa"
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CajaText(
                        modifier = Modifier.weight(1f),
                        value = viewModel.cargo,
                        newValue = { viewModel.cargo = it },
                        label = "Cargo/Área"
                    )
                    CajaText(
                        modifier = Modifier.weight(1f),
                        value = viewModel.telfEmpresa,
                        newValue = { viewModel.telfEmpresa = it },
                        label = "Telf. empresa"
                    )
                }

                CajaText(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.correo,
                    newValue = { viewModel.correo = it },
                    label = "Email *Si es mas de un correo, separarlo por un punto y coma ';'",
                    isEnabled = viewModel.isCorreoEnabled
                )

                Button(onClick = {
                    viewModel.save(context)
                }) {
                    Text(text = "Grabar")
                }
            }
        }

    }
}