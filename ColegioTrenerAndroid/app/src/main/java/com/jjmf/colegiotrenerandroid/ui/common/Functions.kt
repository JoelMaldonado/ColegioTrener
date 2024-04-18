package com.jjmf.colegiotrenerandroid.ui.common

import java.text.NumberFormat
import java.util.Locale


fun Double.toSoles() : String {
    return NumberFormat.getCurrencyInstance(Locale("es", "pe")).format(this)
}