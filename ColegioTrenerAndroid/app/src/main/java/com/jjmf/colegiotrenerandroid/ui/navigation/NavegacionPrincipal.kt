package com.jjmf.colegiotrenerandroid.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjmf.colegiotrenerandroid.ui.features.Login.LoginScreen
import com.jjmf.colegiotrenerandroid.ui.features.Menu.MenuScreen

@Composable
fun NavegacionPrincipal() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Rutas.Login.route
    ) {

        composable(Rutas.Login.route) {
            LoginScreen(
                toMenu = {
                    navController.navigate(Rutas.Menu.route)
                }
            )
        }

        composable(Rutas.Menu.route) {
            MenuScreen(
                logout = {
                    navController.navigate(Rutas.Login.route){
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}