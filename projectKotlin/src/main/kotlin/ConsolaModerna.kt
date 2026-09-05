package org.example

class ConsolaModerna(
    codigo: String,
    marca: String,
    modelo: String,
    tipoUsuario: String
) : Consola(
    codigo,
    marca,
    modelo,
    tipoUsuario
) {
    val tarifaBase: Double = 1500.0
}