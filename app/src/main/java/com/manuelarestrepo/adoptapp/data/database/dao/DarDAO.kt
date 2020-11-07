package com.manuelarestrepo.adoptapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.manuelarestrepo.adoptapp.data.database.entities.Voluntarios

@Dao
interface DarDAO {
    @Insert
    fun insertVoluntario(voluntario: Voluntarios)
}