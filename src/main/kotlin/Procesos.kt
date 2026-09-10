package org.example

import kotlinx.coroutines.delay

val historialTickets = mutableListOf<Ticket>()
var contadorTickets = 1

// Validaciones KOT-012
fun validarCodigo(codigo: String): Boolean {
    val regex = Regex("^[A-Za-z]{2}[0-9]{2}[A-Za-z]{2}$")
    return regex.matches(codigo)
}

fun validarTarifa(
    consola: Consola,
    minutos: Int,
    monto: Double
): Boolean {
    if (monto < 0.0) return false
    if (monto == 0.0 && !(consola is ConsolaModerna && minutos < 20)) return false
    return true
}

suspend fun registrarEntrada(
    puestos: MutableList<Puesto>,
    consola: Consola
): Boolean {
    return try {
        if (!validarCodigo(consola.codigo)) {
            throw IllegalArgumentException("Código de consola inválido: ${consola.codigo}")
        }

        val puesto = puestos.firstOrNull { it.estado is EstadoPuesto.Libre }
            ?: run {
                println("Error: No existen puestos disponibles")
                return false
            }

        puesto.estado = EstadoPuesto.EnProceso("registrando entrada")
        println("Puesto ${puesto.numero}: Registrando entrada...")

        delay(3000)

        puesto.estado = EstadoPuesto.EnJuego(consola)
        println("Puesto ${puesto.numero}: Entrada registrada (${consola.codigo}).")
        true
    } catch (e: IllegalArgumentException) {
        println("Error al registrar entrada: ${e.message}")
        false
    } catch (e: Exception) {
        println("Error inesperado en entrada: ${e.message}")
        false
    }
}
suspend fun registrarSalida(
    puestos: MutableList<Puesto>,
    codigoConsola: String,
    minutosUso: Int
): Ticket? {
    return try {
        if (!validarCodigo(codigoConsola)) {
            throw IllegalArgumentException("El código '$codigoConsola' no tiene formato válido")
        }

        val puesto = puestos.firstOrNull { p ->
            val estado = p.estado
            estado is EstadoPuesto.EnJuego && estado.consola.codigo == codigoConsola
        } ?: run {
            println("Error: Consola no encontrada ($codigoConsola no está en juego)")
            return null
        }

        val estadoActual = puesto.estado as EstadoPuesto.EnJuego
        val consola = estadoActual.consola

        puesto.estado = EstadoPuesto.EnProceso("calculando tarifa")
        println("Puesto ${puesto.numero}: Calculando tarifa de salida...")

        delay(6500)

        val montoBase = consola.calcularTarifa(minutosUso)
        if (!validarTarifa(consola, minutosUso, montoBase)) {
            puesto.estado = EstadoPuesto.EnJuego(consola)
            throw IllegalStateException("Tarifa calculada inválida ($$montoBase)")
        }

        val montoConIva = montoBase * 1.19
        val monto = if (consola.tipoUsuario == "educacional") {
            montoConIva * 0.50
        } else {
            montoConIva
        }

        val ticket = Ticket(
            numero = contadorTickets++,
            codigoConsola = consola.codigo,
            tipoConsola = consola.javaClass.simpleName.replace("Consola", ""),
            tipoUsuario = consola.tipoUsuario,
            minutosUso = minutosUso,
            monto = monto
        )
        historialTickets.add(ticket)

        puesto.estado = EstadoPuesto.Libre
        println("Puesto ${puesto.numero}: Salida procesada. Ticket #${ticket.numero} por $$monto.")
        ticket
    } catch (e: IllegalArgumentException) {
        println("Error de validación en salida: ${e.message}")
        null
    } catch (e: IllegalStateException) {
        println("Error de tarifa en salida: ${e.message}")
        null
    } catch (e: Exception) {
        println("Error inesperado en salida: ${e.message}")
        null
    }
}