package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Inscripciones.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jjmf.colegiotrenerandroid.core.Result
import com.jjmf.colegiotrenerandroid.domain.model.Inscripcion
import com.jjmf.colegiotrenerandroid.domain.repository.InscripcionesRepository
import com.jjmf.colegiotrenerandroid.ui.components.SwitchTrener
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.util.show
import com.jjmf.colegiotrenerandroid.util.toSoles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@Composable
fun ItemInscripcion(
    ctacli: String,
    data: Inscripcion,
    viewModel: ItemInscripcionViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    viewModel.error?.let {
        context.show(it)
        viewModel.error = null
    }
    val bool = remember { mutableStateOf(data.estadoinscripcion) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(text = "Libro:", fontSize = 14.sp)
            Text(text = data.inscripcion.toString(), fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "NH: ${data.codinscripcion}", fontSize = 12.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Importe ${data.precio.toSoles()}", fontSize = 12.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Inscribir?", fontSize = 12.sp)

            SwitchTrener(
                modifier = Modifier.padding(start = 8.dp),
                bool = bool.value,
                newValue = {
                    bool.value = it
                    if (it){
                        viewModel.save(
                            ctacli = ctacli,
                            codTipoInscripcion = data.codtipoinscripcion ?: return@SwitchTrener,
                            codInscripcion = data.codinscripcion ?: return@SwitchTrener
                        )
                        bool.value = true
                    } else {
                        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).apply {
                            titleText = "Warning"
                            contentText = "No se puede realizar esta acción"
                            confirmButtonBackgroundColor = ColorP1.hashCode()
                            setConfirmButton("Continuar"){
                                bool.value = true
                                dismissWithAnimation()
                            }
                            setCancelable(false)
                            show()
                        }
                    }
                }
            )
        }
    }
}

@HiltViewModel
class ItemInscripcionViewModel @Inject constructor(
    private val repository: InscripcionesRepository
) : ViewModel() {

    var error by mutableStateOf<String?>(null)
    fun save(
        ctacli: String,
        codTipoInscripcion: String,
        codInscripcion: String
    ) {
        viewModelScope.launch {
            try {
                val res = repository.save(
                    ctacli = ctacli,
                    codTipoInscripcion = codTipoInscripcion,
                    codInscripcion = codInscripcion
                )
                error = when (res) {
                    is Result.Correcto -> "Actualizado"
                    is Result.Error -> res.mensaje
                }
            } catch (e: Exception) {
                error = e.message
            }
        }
    }

}