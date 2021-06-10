package com.example.kotlinfistapp.data.source.local.database.dbProfilListeToDo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinfistapp.data.model.ProfilListeToDo

@Dao
interface ProfilListeToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdate(profils: List<ProfilListeToDo>)

    @Query("SELECT * FROM PROFILLISTETODO")
    suspend fun getProfils(): List<ProfilListeToDo>
}