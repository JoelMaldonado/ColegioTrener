package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Tareas.Pendientes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jjmf.colegiotrenerandroid.ui.components.CircleText
import com.jjmf.colegiotrenerandroid.ui.theme.ColorGreen
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareasPendientesScreen(
    viewModel: TareasPendientesViewModel = hiltViewModel()
) {

    val fecha = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        DatePicker(
            state = fecha,
            showModeToggle = false,
            title = null,
            headline = null
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CircleText(text = "No hizo tarea", color = ColorT1)
            CircleText(text = "Pendiente", color = ColorYellow)
            CircleText(text = "Revisado", color = ColorGreen)
        }

    }

}

