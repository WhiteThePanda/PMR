package com.example.kotlinfistapp.data.source.local.database.dbItemToDo

import android.content.ClipData
import androidx.room.*
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo

@Dao
interface ItemToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdate(items: List<ItemToDo>)

    //Récupérer les items d'une liste spécifique désignée par son id
    @Query("SELECT * FROM ITEMTODO WHERE IDlist = :id")
    suspend fun getItems(id:String): List<ItemToDo>

    //Récupérer l'item dans la table ITEM désignée par son id
    @Query("SELECT * FROM ITEMTODO WHERE ID = :id")
    suspend fun getOneItem(id:String): ItemToDo

    //Ajouter un item dans une liste
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItems(item:ItemToDo)

    //Change l'état d'un item dans une liste
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun changeItem(item: ItemToDo)
}
