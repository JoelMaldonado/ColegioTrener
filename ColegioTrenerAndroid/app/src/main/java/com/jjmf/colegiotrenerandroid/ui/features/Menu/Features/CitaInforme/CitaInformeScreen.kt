package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.CitaInforme

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.util.unpackFloat1
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.SelectYear
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.CardPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPago
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components.ItemPagoExtend
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.util.show
import java.time.LocalDate

@Composable
fun CitaInformeScreen(
    viewModel: CitaInformeViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SelectYearCita(
            fecha = viewModel.fecha,
            trimestre = viewModel.trimestre,
            listar = {
                viewModel.listarCitas()
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.isLoading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(top = 30.dp),
                        color = ColorP1
                    )
                }
            } else {
                if (viewModel.list.isEmpty()){
                    item {
                        Text(text = "Sin Resultados", color = Color.Gray)
                    }
                } else {
                    items(viewModel.list) {
                        CardPago(
                            title = "${it.nalumno}",
                            modifier = Modifier.fillMaxWidth(),
                            content = {
                                ItemPago(
                                    label = "Fecha",
                                    text = "${it.fechacita}",
                                    ic = Icons.Default.CalendarMonth
                                )
                                ItemPago(
                                    label = "Horario",
                                    text = "${it.horario}",
                                    ic = Icons.Default.AccessTime
                                )
                                ItemPago(
                                    label = "Clase",
                                    text = "${it.clase}",
                                    ic = Icons.Default.DoorFront
                                )
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    ItemPagoExtend(
                                        label = "Observación",
                                        text = "${it.observa?.ifEmpty { "--" }}",
                                        ic = Icons.Default.Visibility,
                                        citaInforme = it,
                                        onClick = {
                                            try{
                                                it.linkInforme?.let {
                                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                                    context.startActivity(intent)
                                                }?:run {
                                                    context.show("Informe no disponible")
                                                }
                                            }catch (ex:Exception){
                                                context.show("No pudimos obtener el informe solicitado")
                                            }
                                        }
                                    )
                                }

                            }
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun SelectYearCita(
    fecha: MutableState<LocalDate>,
    trimestre: MutableState<Trimestre>,
    listar: () -> Unit
) {
    val bool = remember { mutableStateOf(false) }
    val list = remember {
        listOf(
            fecha.value,
            fecha.value.minusYears(1),
            fecha.value.minusYears(2),
            fecha.value.minusYears(3),
            fecha.value.minusYears(4)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .background(ColorS1)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Año", color = Color.White, fontSize = 14.sp)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize(Alignment.CenterEnd)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.White, RoundedCornerShape(4.dp))
                        .clickable {
                            bool.value = !bool.value
                        }
                        .padding(start = 12.dp)
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = fecha.value.year.toString(),
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    val ic = if (bool.value) Icons.Default.ArrowDropUp
                    else Icons.Default.ArrowDropDown
                    Icon(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        imageVector = ic,
                        contentDescription = null,
                        tint = Color.White
                    )
                }


                DropdownMenu(
                    expanded = bool.value,
                    onDismissRequest = { bool.value = false }
                ) {
                    list.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(text = it.year.toString())
                            },
                            onClick = {
                                fecha.value = it
                                bool.value = false
                                listar()
                            }
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Trimestre.entries.forEach { trim ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    RadioButton(
                        selected = trimestre.value == trim,
                        onClick = {
                            trimestre.value = trim
                            listar()
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.White
                        )
                    )
                    Text(text = trim.nombre, fontSize = 12.sp, color = Color.White)
                }
            }
        }
    }
}
