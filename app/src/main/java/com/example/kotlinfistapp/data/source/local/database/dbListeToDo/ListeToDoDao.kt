package com.example.kotlinfistapp.data.source.local.database.dbListeToDo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo

@Dao
interface ListeToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdate(listes: List<ListeToDo>)

    @Query("SELECT * FROM LISTETODO")
    suspend fun getListes(): List<ListeToDo>
}