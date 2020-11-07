package com.manuelarestrepo.adoptapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.manuelarestrepo.adoptapp.data.database.entities.Usuario

@Dao
interface VoluntariosDAO {
    @Insert
    fun insertUsuario(usuario: Usuario)
}