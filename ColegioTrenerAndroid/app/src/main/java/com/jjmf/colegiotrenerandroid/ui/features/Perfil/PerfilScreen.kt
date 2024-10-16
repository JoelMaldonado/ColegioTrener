package com.jjmf.colegiotrenerandroid.ui.features.Perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.colegiotrenerandroid.R
import com.jjmf.colegiotrenerandroid.ui.features.Menu.MenuViewModel
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1

@Composable
fun PerfilScreen(
    logout:()->Unit,
    viewModel: PerlfilViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable {
                    SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
                        titleText = "Cerrar sesión"
                        contentText = "Se cerrara la sesión"
                        cancelButtonBackgroundColor = ColorS1.hashCode()
                        confirmButtonBackgroundColor = ColorP1.hashCode()
                        setCancelButton("Cancelar"){
                            dismissWithAnimation()
                        }
                        setConfirmButton("Confirmar"){
                            dismissWithAnimation()
                            viewModel.logOut()
                            logout()
                        }
                        show()
                    }
                }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )
            Text(text = "Cerrar Sesión")
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Logout,
                contentDescription = null,
                tint = ColorP1
            )
        }
    }
}