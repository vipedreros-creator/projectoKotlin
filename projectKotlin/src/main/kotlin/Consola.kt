package org.example

open class Consola(
    val codigo: String,
    val marca: String,
    val modelo: String,
    val tipoUsuario: String
) {
    open fun calcularTarifa(minutos: Int): Double {
        return 0.0
    }
}