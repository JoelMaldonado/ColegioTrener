package com.jjmf.colegiotrenerandroid.ui.features.Inicio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jjmf.colegiotrenerandroid.R
import com.jjmf.colegiotrenerandroid.ui.features.Inicio.components.ItemInicio
import com.jjmf.colegiotrenerandroid.ui.features.Inicio.components.TextInicio
import com.jjmf.colegiotrenerandroid.ui.navigation.Rutas

@Composable
fun InicioScreen(
    navMenu: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        /** Administrativos **/
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            TextInicio(text = "Administrativos")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                ItemInicio(
                    text = "Datos",
                    ic = R.drawable.ic_datos,
                    click = {
                        navMenu.navigate(Rutas.Administrativos.Datos.route)
                    }
                )

                ItemInicio(
                    text = "Pagos",
                    ic = R.drawable.ic_pagos,
                    click = {
                        navMenu.navigate(Rutas.Administrativos.Pagos.route)
                    }
                )

                ItemInicio(
                    text = "Inscripciones",
                    ic = R.drawable.ic_inscripciones,
                    click = {
                        navMenu.navigate(Rutas.Administrativos.Inscripciones.route)
                    }
                )
            }

        }

        /** Asistencia **/
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            TextInicio(text = "Asistencia")

            ItemInicio(
                text = "Diaria y acumulada",
                ic = R.drawable.ic_diaria_acumulada,
                isVertical = false,
                click = {
                    navMenu.navigate(Rutas.Asistencia.DiariaAcumulada.route)
                }
            )
        }

        /** Tareas **/
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            TextInicio(text = "Tareas")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ItemInicio(
                    text = "Pendientes",
                    ic = R.drawable.ic_pendientes,
                    click = {
                        navMenu.navigate(Rutas.Tareas.Pendientes.route)
                    }
                )

                ItemInicio(
                    text = "Incumplimientos",
                    ic = R.drawable.ic_incumplimientos,
                    click = {
                        navMenu.navigate(Rutas.Tareas.Incumplimientos.route)
                    }
                )
            }
        }

        /** Resultados académicos **/
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            TextInicio(text = "Resultados académicos")

            ItemInicio(
                text = "Cita informe",
                ic = R.drawable.ic_cita_informe,
                isVertical = false,
                click = {
                    navMenu.navigate(Rutas.Resultados.CitaInforme.route)
                }
            )
        }

        /** Autorizaciones **/
        Column(
            modifier = Modifier.fillMaxWidth()
        ){
            TextInicio(text = "Autorizaciones")

            ItemInicio(
                text = "Autorizaciones",
                ic = R.drawable.ic_autorizaciones,
                isVertical = false,
                click = {
                    navMenu.navigate(Rutas.Autorizaciones.route)
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {

            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_intranet),
                contentDescription = null,
                modifier = Modifier.width(30.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Intranet Web",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

    }
}
