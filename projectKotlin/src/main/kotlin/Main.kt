package org.example

import kotlinx.coroutines.runBlocking

// ==========================================
// FUNCIONES DE MISIONES ANTERIORES
// ==========================================

// KOT-003: Funciones de cálculo
fun calcularCostoBase(minutos: Int, tarifaHora: Double): Double {
    return (minutos / 60.0) * tarifaHora
}

fun aplicarIva(monto: Double): Double {
    return monto * 1.19
}

// KOT-004: Decisión y Reglas de Usuario
fun describirTipoUsuario(tipoUsuario: String): String {
    return when (tipoUsuario) {
        "infantil" -> "Usuario infantil"
        "socio" -> "Usuario socio"
        "educacional" -> "Usuario educacional"
        else -> "Tipo de usuario inválido"
    }
}

fun aplicarBeneficioUsuario(
    monto: Double,
    tipoUsuario: String
): Double {
    return when (tipoUsuario) {
        "socio" -> monto * 0.80
        "educacional" -> monto * 0.50
        "infantil" -> monto
        else -> monto
    }
}

// ==========================================
// FUNCIÓN PRINCIPAL MAIN (RUNBLOCKING PARA KOT-011)
// ==========================================

fun main() = runBlocking {
    println("=== GAMEZONE INICIADO ===")

    // KOT-002: Variables
    val nombreSistema: String = "GameZone"
    val capacidad: Int = 10
    var recaudacionTotal: Double = 0.0

    val codigoConsola: String = "CC12CD"
    val marca: String = "PlayStation"
    val modelo: String = "PlayStation 5"
    val minutosUso: Int = 75
    val tarifaHora: Double = 800.0

    println("Sistema: $nombreSistema | Capacidad: $capacidad | Recaudación inicial: $recaudacionTotal")
    println("Consola inicial: $codigoConsola | $marca $modelo | Minutos: $minutosUso | Tarifa: $tarifaHora")

    recaudacionTotal = 800.0
    println("Nueva recaudación: $recaudacionTotal")

    // KOT-003: Cálculos de costo e IVA
    println("\n--- KOT-003 ---")
    val costoBase = calcularCostoBase(minutosUso, tarifaHora)
    println("Costo base ($minutosUso min): $$costoBase")

    val totalConIva = aplicarIva(costoBase)
    println("Total con IVA: $$totalConIva")

    // KOT-004: Evaluación de Tipo de Usuario
    println("\n--- KOT-004 ---")
    val tipoUsuarioPrueba = "socio"
    println(describirTipoUsuario(tipoUsuarioPrueba))

    val montoPrueba = 10000.0
    println("Monto infantil: $${aplicarBeneficioUsuario(montoPrueba, "infantil")}")
    println("Monto socio: $${aplicarBeneficioUsuario(montoPrueba, "socio")}")
    println("Monto educacional: $${aplicarBeneficioUsuario(montoPrueba, "educacional")}")

    if (tipoUsuarioPrueba == "socio") {
        println("Tiene beneficio de socio")
    } else {
        println("No tiene beneficio de socio")
    }

    // KOT-005: Instancia de Clase Base Consola
    println("\n--- KOT-005 ---")
    val consolaBase = Consola(
        codigo = "CC12CD",
        marca = "Sony",
        modelo = "PlayStation 5",
        tipoUsuario = "socio"
    )
    println("Consola creada: ${consolaBase.codigo} - ${consolaBase.marca} ${consolaBase.modelo}")

    // KOT-006: Jerarquía de Consolas
    println("\n--- KOT-006 ---")
    val clasica = ConsolaClasica("CC12CD", "Sony", "PlayStation 2", "socio")
    val moderna = ConsolaModerna("CM22TO", "Nintendo", "Switch", "infantil")
    val vr = ConsolaVR("VR44RG", "Meta", "Quest 3", "educacional", true)

    println("Clásica: ${clasica.modelo} | Tarifa: $${clasica.tarifaBase}")
    println("Moderna: ${moderna.modelo} | Tarifa: $${moderna.tarifaBase}")
    println("VR: ${vr.modelo} | Tarifa: $${vr.tarifaBase} | Premium: ${vr.accesoriosPremium}")

    // KOT-007: Polimorfismo
    println("\n--- KOT-007 ---")
    val listaConsolas: List<Consola> = listOf(clasica, moderna, vr)
    for (c in listaConsolas) {
        println("Consola ${c.modelo} - Tarifa polimórfica (60 min): $${c.calcularTarifa(60)}")
    }

    // KOT-008: Estados del Puesto
    println("\n--- KOT-008 ---")
    val puestoDemo = Puesto(1)
    println("Estado inicial puesto 1: ${describirEstado(puestoDemo)}")
    puestoDemo.estado = EstadoPuesto.EnProceso("registrando entrada")
    println("Estado cambiado: ${describirEstado(puestoDemo)}")

    // KOT-010: Consultas Funcionales
    println("\n--- KOT-010 ---")
    val ticketsPrueba: List<Ticket> = listOf(
        Ticket(1, "CC12CD", "Clasica", 75, 1200.0),
        Ticket(2, "CM22TO", "Moderna", 18, 0.0),
        Ticket(3, "VR44RG", "VR", 120, 7000.0)
    )

    val ticketsVR = ticketsPrueba.filter { it.tipoConsola == "VR" }
    val codigosAtendidos = ticketsPrueba.map { it.codigoConsola }
    val recaudacionTotalTickets = ticketsPrueba.sumOf { it.monto }

    println("Tickets VR encontrados: ${ticketsVR.size}")
    println("Códigos de consolas atendidas: $codigosAtendidos")
    println("Recaudación total tickets: $$recaudacionTotalTickets")

    // KOT-011: Operaciones Asíncronas
    println("\n--- KOT-011 ---")
    val puestos = MutableList(10) { indice -> Puesto(indice + 1) }

    println("[1] Intentando registrar entrada...")
    registrarEntrada(puestos, clasica)

    println("\n[2] Intentando registrar salida...")
    registrarSalida(puestos, "CC12CD", 75)

    println("\n=== SIMULACIÓN COMPLETA FINALIZADA CON ÉXITO ===")
}

