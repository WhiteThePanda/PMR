package com.example.kotlinfistapp.data.source.local

import android.app.Application
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo

class LocalDataSource(
    application: Application
) {

    fun getUsersFromAPI(): List<ProfilListeToDo> {
        TODO()
    }

    fun getListsOfTheUserFromAPI(): MutableList<ListeToDo> {
        TODO()
    }

    fun saveOrUpdateLists(it: MutableList<ListeToDo>) {
        TODO()
    }
    
    fun saveOrUpdateItems(it: MutableList<ItemToDo>) {
        TODO()
    }

    fun getItemOfTheList(id: String): List<ItemToDo> {
        TODO()
    }

    fun saveOrUpdateItemInTheList(it: Unit) {

    }

    fun changeItemInTheList(idList: String, idItem: String, checked: String) {

    }

    fun addItemInTheList(id: String, label: String) {

    }


}
