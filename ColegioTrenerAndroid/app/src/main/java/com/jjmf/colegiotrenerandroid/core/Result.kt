package com.jjmf.colegiotrenerandroid.core

sealed class Result<out T> {
    data class Correcto<T>(val datos: T?) : Result<T>()
    data class Error(val mensaje: String?) : Result<Nothing>()
    //data object Unauthorized : Result<Nothing>()
}