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

    override fun calcularTarifa(minutos: Int): Double {
        val costoBase = (minutos / 60.0) * tarifaBase
        return if (accesoriosPremium) {
            costoBase * 1.30
        } else {
            costoBase
        }
    }
}