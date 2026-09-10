package org.example

// Cantidad de puestos disponibles (libres)
fun puestosDisponibles(puestos: List<Puesto>): Int {
    return puestos.count { it.estado is EstadoPuesto.Libre }
}

// Historial de tickets de consolas utilizadas por socios
fun historialSocios(tickets: List<Ticket>): List<Ticket> {
    return tickets.filter { it.tipoUsuario == "socio" }
}

// Promedio de ingreso por consola atendida
fun promedioIngreso(tickets: List<Ticket>): Double {
    if (tickets.isEmpty()) return 0.0
    return tickets.sumOf { it.monto } / tickets.size
}

// Códigos de las consolas atendidas
fun codigosAtendidos(tickets: List<Ticket>): List<String> {
    return tickets.map { it.codigoConsola }
}

// Consola con mayor tiempo de uso
fun consolaMayorTiempoUso(tickets: List<Ticket>): Ticket? {
    return tickets.maxByOrNull { it.minutosUso }
}

fun reporteCierre(tickets: List<Ticket>, puestos: List<Puesto>) {
    println("\n========== REPORTE DE CIERRE ==========")

    println("\n--- Detalle por consola atendida ---")
    tickets.forEach { t ->
        println("Ticket #${t.numero} | ${t.tipoConsola} | ${t.codigoConsola} | ${t.minutosUso} min | $${t.monto}")
    }

    val recaudacionTotal = tickets.sumOf { it.monto }
    val cantidadAtendidas = tickets.size
    val promedio = promedioIngreso(tickets)
    val tipoMayorIngreso = tickets
        .groupBy { it.tipoConsola }
        .mapValues { (_, lista) -> lista.sumOf { it.monto } }
        .maxByOrNull { it.value }
        ?.key ?: "N/A"

    println("\n--- Totales generales ---")
    println("Recaudación total: $$recaudacionTotal")
    println("Cantidad de consolas atendidas: $cantidadAtendidas")
    println("Promedio de ingreso: $$promedio")
    println("Tipo de consola con mayor ingreso: $tipoMayorIngreso")
    println("Puestos disponibles: ${puestosDisponibles(puestos)}")

    println("========================================")
}