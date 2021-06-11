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

    suspend fun getUsers(): List<ProfilListeToDo> {
        return profilListeToDoDao.getProfils()
    }

    suspend fun getListsOfTheUser(): MutableList<ListeToDo> {
        return listeToDoDao.getListes().toMutableList()
    }

    suspend fun getItemOfTheList(id: String): MutableList<ItemToDo> {
        return itemToDoDao.getItems(id).toMutableList()
    }

    suspend fun saveOrUpdateProfils(it: MutableList<ProfilListeToDo>) {
        return profilListeToDoDao.saveOrUpdate(it)
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
        var newItem : ItemToDo = itemToDoDao.getOneItem(idItem)
        itemToDoDao.addItems(newItem)
    }

    suspend fun addItemInTheList(id: String, label: String) {
    }


}
