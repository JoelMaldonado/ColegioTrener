package com.jjmf.colegiotrenerandroid.util

import android.content.Context
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1

fun Context.show(text:String?) {
    Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
}


fun Context.alertDelete(
    click: () -> Unit
) {
    SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).apply {
        titleText = "¿Estás seguro?"
        contentText = "Esta acción no se puede deshacer"
        setCancelButton("Cancelar") {
            dismissWithAnimation()
        }
        setConfirmButton("Si, eliminar") {
            dismissWithAnimation()
            click()
        }
        confirmButtonBackgroundColor = ColorP1.hashCode()
        cancelButtonBackgroundColor = ColorS1.hashCode()
        show()
    }
}