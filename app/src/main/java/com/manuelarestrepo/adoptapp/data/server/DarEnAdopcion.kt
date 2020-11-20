package com.manuelarestrepo.adoptapp.data.server

data class DarEnAdopcion(
    val id: String?, val nombrePropietario: String, val apellidoPropietario: String,
    val correoPropietario: String,
    val nombrePerroDar: String,
    val edadPerroDar: String,
    val tamanoPerroDar: String,
    val sexoPerroDar: String,
    val esterilizadoPerroDar: String,
    val vacunasPerroDar: String,
    val justificacion: String
)