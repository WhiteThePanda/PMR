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

    fun getListsOfTheUserFromAPI(): List<ListeToDo> {
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


}
