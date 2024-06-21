package com.jjmf.colegiotrenerandroid.util

import com.google.gson.Gson
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun String.capitalize(full: Boolean = false): String {
    return if (full){
        this.split(" ").joinToString(" ") { it.lowercase().replaceFirstChar { it.uppercase() } }
    } else {
        this.lowercase().replaceFirstChar { it.uppercase() }
    }
}

fun Double.toSoles(): String {
    return NumberFormat.getCurrencyInstance(Locale("es", "PE")).format(this)
}

fun Date.format(pattern:String = "dd/MM/yyyy"): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}

fun LocalDate.format(pattern: String): String {
    return format(DateTimeFormatter.ofPattern(pattern))
}

fun String.toDate(): Date {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    return format.parse(this) ?: Date()
}


inline fun <reified T> convertJson(data: String?): T {
    return try {
        Gson().fromJson(data, T::class.java)
    } catch (e: Exception) {
        throw IllegalArgumentException("No se pudo parsear")
    }
}