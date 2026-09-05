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

    override fun calcularTarifa(minutos: Int): Double {
        if (minutos < 20) {
            return 0.0
        }
        return (minutos / 60.0) * tarifaBase
    }
}