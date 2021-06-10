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
        return listeToDoDao.getListes()
    }

    suspend fun getItemOfTheList(id: String): List<ItemToDo> {
        return itemToDoDao.getItems(id)
    }

    suspend fun saveOrUpdateProfils(it: MutableList<ProfilListeToDo>) {
        return profilListeToDoDao.saveOrUpdate(it)
    }
    
    suspend fun saveOrUpdateLists(items: MutableList<ListeToDo>) {
        return listeToDoDao.saveOrUpdate(items)
    }

    suspend fun saveOrUpdateItems(item: MutableList<ItemToDo>) {
        return itemToDoDao.saveOrUpdate(item)
    }

    suspend fun changeItemInTheList(idList: String, idItem: String, checked: String) {
        var newItem : ItemToDo = itemToDoDao.getOneItem(idItem)
        newItem.switchCheck(checked)
        itemToDoDao.addItems(newItem)
    }

    suspend fun addItemInTheList(id: String, label: String) {
        var item = ItemToDo(label)
        itemToDoDao.addItems(item)
    }


}
