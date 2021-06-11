package com.example.kotlinfistapp.data.source.local

import android.app.Application
import android.content.ClipData
import androidx.room.Room
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo
import com.example.kotlinfistapp.data.source.local.database.ToDoDatabase

class LocalDataSource(
    application: Application
) {
    private val roomDatabase =
            Room.databaseBuilder(application, ToDoDatabase::class.java, "room-database").build()

    private val profilListeToDoDao = roomDatabase.profilListeToDoDao()
    private val listeToDoDao = roomDatabase.listeToDoDao()
    private val itemToDoDao = roomDatabase.itemToDoDao()

    suspend fun getListsOfTheUser(pseudo: String): MutableList<ListeToDo> {
        return listeToDoDao.getListes(pseudo).toMutableList()
    }

    suspend fun getItemOfTheList(id: String): MutableList<ItemToDo> {
        return itemToDoDao.getItems(id).toMutableList()
    }

    suspend fun saveOrUpdateProfil(pseudo: String,mdp : String) {
        val newProfil : ProfilListeToDo = ProfilListeToDo(pseudo,mdp)
        return profilListeToDoDao.saveOrUpdate(newProfil)
    }
    
    suspend fun saveOrUpdateLists(items: MutableList<ListeToDo>,pseudo:String) {
        for (item in items)
        {
            item.login=pseudo
        }
        return listeToDoDao.saveOrUpdate(items)
    }

    suspend fun saveOrUpdateItems(items: MutableList<ItemToDo>,idList: String) {
        for (item in items)
        {
            item.idList=idList
        }
        return itemToDoDao.saveOrUpdate(items)
    }

    suspend fun changeItemInTheList(idList: String, idItem: String, checked: String) {
        val newItem : ItemToDo = itemToDoDao.getOneItem(idItem)
        newItem.faitText=checked
        itemToDoDao.changeItem(newItem)
    }

    suspend fun addItemInTheList(id: String, label: String) {
    }

    suspend fun getUser(pseudo: String, mdp: String) : ProfilListeToDo
    {
        return profilListeToDoDao.getUser(pseudo,mdp)
    }


}
