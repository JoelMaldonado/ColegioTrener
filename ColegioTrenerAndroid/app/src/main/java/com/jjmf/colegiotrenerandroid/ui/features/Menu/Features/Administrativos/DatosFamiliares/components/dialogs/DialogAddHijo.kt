package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.DatosFamiliares.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jjmf.colegiotrenerandroid.data.services.request.DataHijoRequest
import com.jjmf.colegiotrenerandroid.ui.components.BoxForm
import com.jjmf.colegiotrenerandroid.ui.components.BoxFormField
import com.jjmf.colegiotrenerandroid.ui.theme.ColorFondo
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.util.show
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DialogAddHijo(
    close: () -> Unit,
    add: (DataHijoRequest)->Unit
) {

    val context = LocalContext.current

    val nombre = remember { mutableStateOf("") }
    val fecha = remember { mutableStateOf(TextFieldValue()) }

    Dialog(onDismissRequest = close) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .background(ColorFondo)
        ) {
            Text(
                text = "Agregar Hijo",
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorT1)
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BoxForm(
                    value = nombre.value,
                    newValue = { nombre.value = it },
                    icon = Icons.Default.Person,
                    label = "Nombres y apellidos",
                    capitalization = KeyboardCapitalization.Words
                )

                BoxFormField(
                    value = fecha.value,
                    newValue = {
                        val formattedDate = formatDateString(it.text)
                        fecha.value = TextFieldValue(
                            text = formattedDate,
                            selection = TextRange(formattedDate.length)
                        )
                    },
                    icon = Icons.Default.CalendarMonth,
                    label = "Fecha de Nacimiento",
                    keyboardType = KeyboardType.Number
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            if (!isValidDate(fecha.value.text)) {
                                context.show("Fecha Invalida")
                                return@Button
                            }

                            if (nombre.value.isEmpty()) {
                                context.show("Nombre vacio")
                                return@Button
                            }

                            close()

                            val body = DataHijoRequest(
                                accion = "Crear",
                                nombre = nombre.value,
                                fechaNac = fecha.value.text
                            )

                            add(body)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorP1
                        )
                    ) {
                        Text(text = "Agregar")
                    }
                    Button(
                        onClick = close,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorS1
                        )
                    ) {
                        Text(text = "Cancelar")
                    }
                }
            }
        }
    }
}

fun isValidDate(dateString: String): Boolean {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    dateFormat.isLenient = false
    return try {
        dateFormat.parse(dateString)
        val regex = """\d{2}/\d{2}/\d{4}""".toRegex()
        regex.matches(dateString)
    } catch (e: Exception) {
        false
    }
}

fun formatDateString(input: String): String {
    var formattedDate = input.filter { it.isDigit() }

    if (formattedDate.length > 2) {
        formattedDate = formattedDate.substring(0, 2) + "/" + formattedDate.substring(2)
    }
    if (formattedDate.length > 5) {
        formattedDate = formattedDate.substring(0, 5) + "/" + formattedDate.substring(5)
    }
    if (formattedDate.length > 10) {
        formattedDate = formattedDate.substring(0, 10)
    }

    return formattedDate
}