package org.example

class Puesto(
    val numero: Int,
    var estado: EstadoPuesto = EstadoPuesto.Libre
)

fun describirEstado(puesto: Puesto): String {
    return when (val estado = puesto.estado) {
        EstadoPuesto.Libre ->
            "Puesto libre"

        is EstadoPuesto.EnJuego ->
            "En juego: ${estado.consola.codigo}"

        is EstadoPuesto.EnProceso ->
            "Procesando: ${estado.motivo}"

        is EstadoPuesto.EnReparacion ->
            "En reparación: ${estado.motivo}"
    }
}