package org.example

data class Ticket(
    val numero: Int,
    val codigoConsola: String,
    val tipoConsola: String,
    val tipoUsuario: String,
    val minutosUso: Int,
    val monto: Double
)