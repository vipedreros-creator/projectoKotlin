package org.example

class ConsolaClasica(
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
    val tarifaBase: Double = 800.0
}