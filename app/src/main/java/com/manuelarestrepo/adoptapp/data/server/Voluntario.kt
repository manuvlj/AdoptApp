package com.manuelarestrepo.adoptapp.data.server

data class Voluntario(
    val id: String?,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val correo: String,
    val direccion: String,
    val ciudad: String
)