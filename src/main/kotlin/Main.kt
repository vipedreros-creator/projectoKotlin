package org.example

import kotlinx.coroutines.runBlocking

// ==========================================
// FUNCIONES AUXILIARES
// ==========================================

fun calcularCostoBase(minutos: Int, tarifaHora: Double): Double {
    return (minutos / 60.0) * tarifaHora
}

fun aplicarIva(monto: Double): Double {
    return monto * 1.19
}

fun describirTipoUsuario(tipoUsuario: String): String {
    return when (tipoUsuario) {
        "infantil" -> "Usuario infantil"
        "socio" -> "Usuario socio"
        "educacional" -> "Usuario educacional"
        else -> "Tipo de usuario inválido"
    }
}

fun aplicarBeneficioUsuario(monto: Double, tipoUsuario: String): Double {
    return when (tipoUsuario) {
        "socio" -> monto * 0.80
        "educacional" -> monto * 0.50
        "infantil" -> monto
        else -> monto
    }
}

// ==========================================
// FUNCIÓN PRINCIPAL MAIN
// ==========================================

fun main() = runBlocking {
    println("=== GAMEZONE INICIADO ===")

    // KOT-002: Variables y Tipos de datos
    // Tipos explícitos
    val nombreSistema: String = "GameZone"
    val totalPuestosSistema: Int = 10
    val ivaPorcentaje: Double = 0.19
    val descuentoSocio: Double = 0.20
    val descuentoEducacional: Double = 0.50
    val letraSistema: Char = 'G'
    val version: Float = 1.0f
    val ticketsMaximosLong: Long = 1000000L
    var sistemaActivo: Boolean = true

    // Tipos inferidos (el compilador deduce el tipo)
    val nombreCorto = "GZ"          // inferido como String
    val puestosOcupadosInicial = 0  // inferido como Int

    // var vs val: sistemaActivo puede cambiar, los demás no
    sistemaActivo = true

    // Operación aritmética simple usando las variables tipadas
    val puestosLibresInicial = totalPuestosSistema - puestosOcupadosInicial
    val ivaComoPorcentajeEntero = (ivaPorcentaje * 100).toInt()

    println("Sistema: $nombreSistema ($nombreCorto) v$version | Letra: $letraSistema")
    println("Puestos totales: $totalPuestosSistema | Libres al inicio: $puestosLibresInicial")
    println("IVA: $ivaComoPorcentajeEntero% | Activo: $sistemaActivo | Capacidad ticket: $ticketsMaximosLong")

    // Variables originales de KOT-002 (datos de ejemplo del sistema)
    val capacidad: Int = 10
    var recaudacionTotal: Double = 0.0
    val codigoConsola: String = "CC12CD"
    val marca: String = "PlayStation"
    val modelo: String = "PlayStation 5"
    val minutosUso: Int = 75
    val tarifaHora: Double = 800.0

    println("Capacidad: $capacidad")
    println("Recaudación: $recaudacionTotal")
    println("Código consola: $codigoConsola")
    println("Marca: $marca")
    println("Modelo: $modelo")
    println("Minutos de uso: $minutosUso")
    println("Tarifa por hora: $tarifaHora")

    recaudacionTotal = 800.0
    println("Nueva recaudación: $recaudacionTotal")

    // KOT-006 & KOT-007: Consolas y Polimorfismo
    val clasica = ConsolaClasica("CC12CD", "Sony", "PlayStation 5", "socio")
    val moderna = ConsolaModerna("CM22TO", "Nintendo", "Switch", "infantil")
    val vr = ConsolaVR("VR44RG", "Meta", "Quest 3", "educacional", true)

    // KOT-011: Operaciones Asíncronas
    println("\n--- KOT-011: Flujo Normal Asíncrono ---")
    val puestosSimulacion = MutableList(10) { indice -> Puesto(indice + 1) }

    println("[1] Registrando entrada exitosa...")
    registrarEntrada(puestosSimulacion, clasica)

    println("\n[2] Registrando salida exitosa...")
    registrarSalida(puestosSimulacion, "CC12CD", 75)

    // KOT-012: Control de Errores y Validaciones
    println("\n--- KOT-012: Pruebas de Control de Errores ---")
    val puestosKOT012 = MutableList(10) { indice -> Puesto(indice + 1) }

    // Escenario 1: Código de consola inválido
    println("\n[Prueba 1: Código inválido]")
    val consolaInvalida = ConsolaClasica("123ABC", "Sony", "PS5", "socio")
    registrarEntrada(puestosKOT012, consolaInvalida)

    // Escenario 2: Sin capacidad (Llenar los 10 puestos)
    println("\n[Prueba 2: Capacidad Completa]")
    for (i in 1..10) {
        puestosKOT012[i - 1].estado = EstadoPuesto.EnJuego(
            ConsolaClasica("CC${10 + i}CD", "Sony", "PS5", "socio")
        )
    }
    val consolaExtra = ConsolaClasica("CC99CD", "Sony", "PS5", "socio")
    registrarEntrada(puestosKOT012, consolaExtra)

    // Escenario 3: Consola no encontrada
    println("\n[Prueba 3: Consola no encontrada]")
    registrarSalida(puestosKOT012, "XX99YY", 60)

    // Escenario 4: Caso de Tarifa de ConsolaModerna (< 20 min) que SÍ es válida (0.0)
    println("\n[Prueba 4: Cero válido de ConsolaModerna (<20 min)]")
    val puestoPrueba = Puesto(1)
    puestoPrueba.estado = EstadoPuesto.EnJuego(moderna)
    val puestosTarifa = mutableListOf(puestoPrueba)

    registrarSalida(puestosTarifa, "CM22TO", 15)

    println("\n=== SIMULACIÓN KOT-012 FINALIZADA CON ÉXITO ===")

    // KOT-013: Simulación completa de negocio
    println("\n--- KOT-013: Simulación completa de negocio ---")
    val puestosFinal = MutableList(10) { indice -> Puesto(indice + 1) }

    val c1 = ConsolaClasica("CC12CD", "Sony", "PlayStation 5", "socio")
    val c2 = ConsolaClasica("CC99ZA", "Microsoft", "Xbox Series X", "infantil")
    val c3 = ConsolaModerna("CM22TO", "Nintendo", "Switch", "infantil")
    val c4 = ConsolaVR("VR44RG", "Meta", "Quest 3", "educacional", true)
    val c5 = ConsolaVR("VR77RG", "HTC", "Vive Pro", "infantil", false)

    registrarEntrada(puestosFinal, c1)
    registrarEntrada(puestosFinal, c2)
    registrarEntrada(puestosFinal, c3)
    registrarEntrada(puestosFinal, c4)
    registrarEntrada(puestosFinal, c5)

    registrarSalida(puestosFinal, "CC12CD", 75)
    registrarSalida(puestosFinal, "CC99ZA", 180)
    registrarSalida(puestosFinal, "CM22TO", 18)
    registrarSalida(puestosFinal, "VR44RG", 120)
    registrarSalida(puestosFinal, "VR77RG", 45)

    println("\n--- KOT-010: Consultas de Negocio ---")
    println("Historial de tickets de socios: ${historialSocios(historialTickets)}")
    println("Códigos de consolas atendidas: ${codigosAtendidos(historialTickets)}")
    println("Consola con mayor tiempo de uso: ${consolaMayorTiempoUso(historialTickets)}")

    reporteCierre(historialTickets, puestosFinal)
}