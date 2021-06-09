package com.example.kotlinfistapp.data.source.local

import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo

class LocalDataSource {

    fun getUsersFromAPI(): List<ProfilListeToDo> {
        TODO()
    }

    fun getListsOfTheUserFromAPI(): List<ListeToDo> {
        TODO()
    }

    fun saveOrUpdateLists(it: MutableList<ListeToDo>) {

    }
    
    fun saveOrUpdateItems(it: MutableList<ItemToDo>) {

    }

    fun getItemOfTheList(id: String): List<ItemToDo> {

    }


}
