package com.jjmf.colegiotrenerandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jjmf.colegiotrenerandroid.ui.navigation.NavegacionPrincipal
import com.jjmf.colegiotrenerandroid.ui.theme.ColegioTrenerAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColegioTrenerAndroidTheme {
                NavegacionPrincipal()
            }
        }
    }
}