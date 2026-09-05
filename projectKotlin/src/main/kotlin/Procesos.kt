package org.example

import kotlinx.coroutines.delay

val historialTickets = mutableListOf<Ticket>()
var contadorTickets = 1

suspend fun registrarEntrada(
    puestos: MutableList<Puesto>,
    consola: Consola
): Boolean {
    val puesto = puestos.firstOrNull { it.estado is EstadoPuesto.Libre } ?: return false

    puesto.estado = EstadoPuesto.EnProceso("registrando entrada")
    println("Puesto ${puesto.numero}: Registrando entrada...")

    delay(3000) // Simula la espera de 3 segundos

    puesto.estado = EstadoPuesto.EnJuego(consola)
    println("Puesto ${puesto.numero}: ¡Entrada registrada! Consola ${consola.codigo} ahora está EnJuego.")
    return true
}

suspend fun registrarSalida(
    puestos: MutableList<Puesto>,
    codigoConsola: String,
    minutosUso: Int
): Ticket? {
    val puesto = puestos.firstOrNull { p ->
        val estado = p.estado
        estado is EstadoPuesto.EnJuego && estado.consola.codigo == codigoConsola
    } ?: run {
        println("No se encontró puesto EnJuego con la consola $codigoConsola")
        return null
    }

    val estadoActual = puesto.estado as EstadoPuesto.EnJuego
    val consola = estadoActual.consola // Guardamos la consola antes de cambiar el estado

    puesto.estado = EstadoPuesto.EnProceso("calculando tarifa")
    println("Puesto ${puesto.numero}: Calculando tarifa de salida...")

    delay(6500) // Simula la espera de 6.5 segundos

    val monto = consola.calcularTarifa(minutosUso)
    val ticket = Ticket(
        numero = contadorTickets++,
        codigoConsola = consola.codigo,
        tipoConsola = consola.javaClass.simpleName.replace("Consola", ""),
        minutosUso = minutosUso,
        monto = monto
    )
    historialTickets.add(ticket)

    puesto.estado = EstadoPuesto.Libre
    println("Puesto ${puesto.numero}: Salida procesada. Ticket #${ticket.numero} por $$monto. Puesto nuevamente Libre.")
    return ticket
}