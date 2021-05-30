package com.example.kotlinfistapp

import java.io.Serializable

class ListeToDo(var title : String) : Serializable {
    private var titreListeToDo : String = title
    private var lesItems : MutableList<ItemToDo> = mutableListOf()
    override fun toString(): String {
        return "ToDoList(_title='$titreListeToDo')"
    }
    public fun GetLesItems() :MutableList<ItemToDo>{
        return lesItems
    }
    public fun GetTitreListeToDo():String{
        return titreListeToDo
    }

}