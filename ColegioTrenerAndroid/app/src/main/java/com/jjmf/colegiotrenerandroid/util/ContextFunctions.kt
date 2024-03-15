package com.jjmf.colegiotrenerandroid.util

import android.content.Context
import android.widget.Toast

fun Context.show(text:String?) {
    Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
}