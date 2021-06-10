package com.example.kotlinfistapp.data.source.local.database.dbItemToDo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo

@Dao
interface ItemToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdate(items: List<ItemToDo>)

    @Query("SELECT * FROM LISTETODO")
    suspend fun getItems(): List<ItemToDo>

}
