package com.example.kotlinfistapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinfistapp.data.model.ProfilListeToDo

@Dao
interface ToDoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdate(profils: List<ProfilListeToDo>)

    @Query("SELECT * FROM POST")
    suspend fun getProfils(): List<ProfilListeToDo>
}