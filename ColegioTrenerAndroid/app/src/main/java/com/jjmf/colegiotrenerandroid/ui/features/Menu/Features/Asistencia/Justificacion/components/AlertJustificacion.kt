package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion.components

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Base64
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jjmf.colegiotrenerandroid.domain.model.Justificacion
import com.jjmf.colegiotrenerandroid.domain.model.Razones
import com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Asistencia.Justificacion.JustificacionViewModel
import com.jjmf.colegiotrenerandroid.ui.theme.ColorP1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.ui.theme.ColorT1
import com.jjmf.colegiotrenerandroid.util.show
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.time.LocalDate

fun saveBase64ToTxtFile(context: Context, base64String: String, fileName: String) {
    try {
        // Create or open a file in the app's private storage
        val file = File(context.filesDir, "$fileName.txt")

        // Write the Base64 string to the file
        FileOutputStream(file).use { outputStream ->
            outputStream.write(base64String.toByteArray())
        }

        println("File saved successfully: ${file.absolutePath}")
    } catch (e: IOException) {
        e.printStackTrace()
        println("Failed to save the file.")
    }
}

@Composable
fun AlertJustificacion(
    showDialog: Boolean,
    dismissOnClickRefresh: () -> Unit,
    dismissOnClick: () -> Unit,
    viewModel: JustificacionViewModel,
    justificacion: Justificacion?
) {

    val context = LocalContext.current

    var data by remember {
        mutableStateOf("")
    }
    var razon : Razones? by remember {
        mutableStateOf(null)
    }
    var detalle  by remember {
        mutableStateOf("")
    }
    var fileName by remember {
        mutableStateOf("Archivo...")
    }

    val singlePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            uri?.let {
                fileName = getFileNameFromUri(context,uri)
                GlobalScope.launch {
                   val result = uriToBase64(it,context)
                    data = result
                    saveBase64ToTxtFile(context, data, "MyBase64File")

                }
            }

            //viewModel.onEvent(CreateRecipeFormEvent.ImageChange(uri))
            //uri = uri2
        }
    )

    if(viewModel.success != null){
        context.show(viewModel.success)
        viewModel.clearSuccess()
        dismissOnClickRefresh()
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ColorT1),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Justificación",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Razón",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    SelectRazones(
                        viewModel,
                        onSelected = {
                            razon = it
                        }
                    )
                }
                razon?.let {
                    if(it.descrip?.trim() == "Otro") {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Detalle",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = detalle,
                            singleLine = false,
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.None),
                            keyboardActions = KeyboardActions(
                                onAny = {

                                }
                            ),
                            onValueChange = {
                                if (!it.contains("\n")) {
                                    detalle = it
                                }

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Adjuntar constancia (opcional)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = Color.Black)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    //horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.weight(3f)) {
                        Text(
                            text = fileName,
                            fontSize = 12.sp
                        )
                    }
                    Box(modifier = Modifier.weight(1.5f)) {
                        Button(
                            onClick = {
                                val mimeTypes = arrayOf("image/*", "application/pdf")
                                singlePhotoLauncher.launch(mimeTypes)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ColorS1
                            ),
                            modifier = Modifier.height(35.dp)
                        ) {
                            Text(
                                text = "Cargar",
                                fontSize = 12.sp
                            )
                        }
                    }


                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            if(razon==null){
                                context.show("Seleccionar la razon")
                                return@Button
                            }

                            viewModel.grabarJustificacion(
                                fecha = "${justificacion?.fecha?.substring(8,10)}/${justificacion?.fecha?.substring(5,7)}/${justificacion?.fecha?.substring(0,4)}",
                                ctacli = viewModel.ctacli,
                                idRazon = razon?.idtipo ?: "",
                                comentario = detalle,
                                fileName = data
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorS1
                        )
                    ) {
                        Text(text = "Grabar")
                    }
                    Button(
                        onClick = { dismissOnClick() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ColorP1
                        )
                    ) {
                        Text(text = "Cancelar")
                    }
                }
            }
        }

    }
}

private fun uriToBase64(uri: Uri, context: Context): String {
    val contentResolver: ContentResolver = context.contentResolver
    val inputStream: InputStream? = contentResolver.openInputStream(uri)
    val bytes: ByteArray? = inputStream?.readBytes()
    inputStream?.close()
    return if (bytes != null) {
        Base64.encodeToString(bytes, Base64.DEFAULT)
    } else {
        ""
    }
}

fun getFileNameFromUri(context: Context, uri: Uri): String {
    var fileName = ""
    uri.scheme?.let { scheme ->
        if (scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    fileName = it.getString(it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
                }
            }
        }
        if (fileName == null) {
            fileName = uri.path!!
            val cut = fileName?.lastIndexOf('/')
            if (cut != -1 && cut != null) {
                fileName = fileName.substring(cut + 1)
            }
        }
    }
    return fileName
}