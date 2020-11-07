package com.manuelarestrepo.adoptapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.manuelarestrepo.adoptapp.data.database.entities.Usuario

@Dao
interface UsuarioDAO {

    @Insert
    fun insertUsuario(usuario: Usuario)

    @Query("SELECT * FROM tabla_usuario WHERE correo LIKE :correo")
    fun searchUsuario(correo: String): Usuario

}