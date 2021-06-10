package com.example.kotlinfistapp.data.source.local.database.dbListeToDo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinfistapp.data.model.ListeToDo

@Dao
interface ListeToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdate(listes: MutableList<ListeToDo>)

    @Query("SELECT * FROM LISTETODO")
    suspend fun getListes(): MutableList<ListeToDo>
}