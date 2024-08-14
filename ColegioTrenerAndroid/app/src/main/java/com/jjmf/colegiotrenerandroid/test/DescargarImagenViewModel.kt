package com.jjmf.colegiotrenerandroid.test

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class DescargarImagenViewModel @Inject constructor(

) : ViewModel(){

    val url = "https://img.freepik.com/psd-gratis/linda-escena-perro-marron-blanco_23-2150179279.jpg"

    val pdf = "https://atmosferasoltec.pse.pe/cpe/08890058-2215-4e82-b9c4-2aecc3a12a74.pdf"

    fun descargarImagen(context: Context) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("tagito", "Error al descargar imagen")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.byteStream()?.let { inputStream ->
                    guardarImagenEnGaleria(inputStream, context)
                }
            }
        })
    }

    private fun guardarImagenEnGaleria(inputStream: InputStream, context: Context) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "Imagen_${System.currentTimeMillis()}.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            resolver.openOutputStream(it).use { outputStream ->
                inputStream.copyTo(outputStream!!)
                println("Imagen guardada con éxito en la galería.")
            }
        }
    }

    fun descargarPDF(context: Context) {
        val client = OkHttpClient()
        val request = Request.Builder().url(pdf).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error al descargar el PDF: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.byteStream()?.let { inputStream ->
                    guardarPDFEnDispositivo(inputStream, context)
                }
            }
        })
    }
    private fun guardarPDFEnDispositivo(inputStream: InputStream, context: Context) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "Archivo_${System.currentTimeMillis()}.pdf")
            put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
            // Nota: Algunos dispositivos pueden requerir rutas diferentes o manejo adicional para almacenamiento con ámbito
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS)
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
        if (uri != null) {
            resolver.openOutputStream(uri).use { outputStream ->
                inputStream.copyTo(outputStream!!)
                println("PDF guardado con éxito en el dispositivo.")
            }
        } else {
            println("Error al guardar el PDF en el dispositivo.")
        }
    }
}