package org.example

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("GameZone iniciado")

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



    val costoBase = calcularCostoBase( minutosUso, tarifaHora )
    println("Costo base: $costoBase")

    val totalConIva = aplicarIva(costoBase)
    println("Total con IVA: $totalConIva")


}


fun calcularCostoBase(minutos: Int, tarifaHora: Double): Double{
    return  (minutos/60.0)*tarifaHora
}

fun aplicarIva(monto: Double): Double{
    return  monto*1.19
}

