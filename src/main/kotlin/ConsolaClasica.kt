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

    override fun calcularTarifa(minutos: Int): Double {
        val costoBase = (minutos / 60.0) * tarifaBase
        return if (tipoUsuario == "socio") {
            costoBase * 0.80
        } else {
            costoBase
        }
    }
}