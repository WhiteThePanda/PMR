package com.example.kotlinfistapp.data.model

import com.example.kotlinfistapp.data.model.ItemToDo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ListeToDo(var title : String) : Serializable {
    @SerializedName("label")
    private var titreListeToDo : String = title
    private var lesItems : MutableList<ItemToDo> = mutableListOf()
    public var id : String =""

    public fun GetId() : String{
        return id
    }

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