package org.example

class ConsolaVR(
    codigo: String,
    marca: String,
    modelo: String,
    tipoUsuario: String,
    val accesoriosPremium: Boolean
) : Consola(
    codigo,
    marca,
    modelo,
    tipoUsuario
) {
    val tarifaBase: Double = 3000.0
}