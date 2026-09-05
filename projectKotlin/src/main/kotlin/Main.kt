package org.example

fun main() {
    println("GameZone iniciado")

    // KOT-002
    val nombreSistema: String = "GameZone"
    val capacidad: Int = 10
    var recaudacionTotal: Double = 0.0

    val codigoConsola: String = "CC12CD"
    val marca: String = "PlayStation"
    val modelo: String = "PlayStation 5"
    val minutosUso: Int = 75
    val tarifaHora: Double = 800.0

    println("Sistema: $nombreSistema")
    println("Capacidad: $capacidad")
    println("Recaudación: $recaudacionTotal")

    println("Código consola: $codigoConsola")
    println("Marca: $marca")
    println("Modelo: $modelo")
    println("Minutos de uso: $minutosUso")
    println("Tarifa por hora: $tarifaHora")

    recaudacionTotal = 800.0

    println("Nueva recaudación: $recaudacionTotal")

    // KOT-003
    val costoBase = calcularCostoBase(minutosUso, tarifaHora)
    println("Costo base: $costoBase")

    val totalConIva = aplicarIva(costoBase)
    println("Total con IVA: $totalConIva")

    // KOT-004
    val tipoUsuario = "socio"

    println(describirTipoUsuario(tipoUsuario))

    val monto = 10000.0

    println("Monto infantil: ${aplicarBeneficioUsuario(monto, "infantil")}")
    println("Monto socio: ${aplicarBeneficioUsuario(monto, "socio")}")
    println("Monto educacional: ${aplicarBeneficioUsuario(monto, "educacional")}")

    if (tipoUsuario == "socio") {
        println("Tiene beneficio de socio")
    } else {
        println("No tiene beneficio de socio")
    }

    // KOT-005
    val consola = Consola(
        codigo = "CC12CD",
        marca = "Sony",
        modelo = "PlayStation 5",
        tipoUsuario = "socio"
    )

    println("Consola creada:")
    println("Código: ${consola.codigo}")
    println("Marca: ${consola.marca}")
    println("Modelo: ${consola.modelo}")
    println("Tipo de Usuario: ${consola.tipoUsuario}")

    // KOT-006 - Jerarquía de Consolas
    val clasica = ConsolaClasica(
        codigo = "CC12CD",
        marca = "Sony",
        modelo = "PlayStation 2",
        tipoUsuario = "socio"
    )

    val moderna = ConsolaModerna(
        codigo = "CM22TO",
        marca = "Nintendo",
        modelo = "Switch",
        tipoUsuario = "infantil"
    )

    val vr = ConsolaVR(
        codigo = "VR44RG",
        marca = "Meta",
        modelo = "Quest 3",
        tipoUsuario = "educacional",
        accesoriosPremium = true
    )

    println("--- KOT-006 ---")
    println("Consola Clásica: ${clasica.modelo} - Tarifa: $${clasica.tarifaBase}")
    println("Consola Moderna: ${moderna.modelo} - Tarifa: $${moderna.tarifaBase}")
    println("Consola VR: ${vr.modelo} - Tarifa: $${vr.tarifaBase} - Premium: ${vr.accesoriosPremium}")

    // KOT-007 - Tarifas Polimórficas
    println("\n--- KOT-007 - Prueba Polimórfica ---")
    val minutosDeUso = 60

    val consolas: List<Consola> = listOf(
        ConsolaClasica("CC12CD", "Sony", "PlayStation 2", "socio"),
        ConsolaModerna("CM22TO", "Nintendo", "Switch", "infantil"),
        ConsolaVR("VR44RG", "Meta", "Quest 3", "educacional", true)
    )

    for (consola in consolas) {
        val tarifa = consola.calcularTarifa(minutosDeUso)
        println("Consola: ${consola.modelo} (${consola.javaClass.simpleName}) - Tarifa calculada: $$tarifa")
    }

    // KOT-008 - Estados de los puestos
    println("\n--- KOT-008 - Prueba de Estados de Puestos ---")
    val puesto = Puesto(1)
    println("Estado inicial: ${describirEstado(puesto)}")

    // Transición a EnProceso
    puesto.estado = EstadoPuesto.EnProceso("registrando entrada")
    println("Nuevo estado: ${describirEstado(puesto)}")

    // Transición a EnJuego
    val consolaVR = ConsolaVR("VR44RG", "Meta", "Quest 3", "educacional", true)
    puesto.estado = EstadoPuesto.EnJuego(consolaVR)
    println("Nuevo estado: ${describirEstado(puesto)}")

    // Transición a EnReparacion
    puesto.estado = EstadoPuesto.EnReparacion("mantenimiento preventivo")
    println("Nuevo estado: ${describirEstado(puesto)}")
}

// KOT-003
fun calcularCostoBase(minutos: Int, tarifaHora: Double): Double {
    return (minutos / 60.0) * tarifaHora
}

fun aplicarIva(monto: Double): Double {
    return monto * 1.19
}

// KOT-004
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


