package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjmf.colegiotrenerandroid.domain.model.Razones
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion.JustificacionViewModel
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectRazones(
    viewModel: JustificacionViewModel,
    onSelected:(Razones?)->Unit
) {

    var isExpanded2 by remember { mutableStateOf(false) }
    var selected : Razones? by remember {
        mutableStateOf(null)
    }
    
    LaunchedEffect(key1 = Unit) {
        viewModel.getListRazones()
    }

    Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)) {
        //Text(text = "Selecciona un item:")
        ExposedDropdownMenuBox(
            expanded = isExpanded2,
            onExpandedChange = { isExpanded2 = !isExpanded2 },
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                value = selected?.descrip ?: "Seleccionar",
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = ColorP1,
                    unfocusedBorderColor = Color.Black,
                ),
                onValueChange = {

                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded2)
                },
                readOnly = true
            )
            ExposedDropdownMenu(
                expanded = isExpanded2,
                onDismissRequest = { isExpanded2 = false }
            ) {
                viewModel.listRazones?.let { razones ->
                    razones.forEachIndexed { index, razon ->
                        DropdownMenuItem(
                            text = {
                                Text(text = razon.descrip?:"")
                            },
                            onClick = {
                                selected = razones[index]
                                isExpanded2 = false
                                onSelected(selected)
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }

        }

    }
}
