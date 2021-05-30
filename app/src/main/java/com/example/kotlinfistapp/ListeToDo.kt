package com.example.kotlinfistapp

import java.io.Serializable

class ListeToDo(var title : String) : Serializable {
    var titreListeToDo : String = title
    var lesItems : MutableList<ItemToDo> = mutableListOf()
    override fun toString(): String {
        return "ToDoList(_title='$titreListeToDo')"
    }

}