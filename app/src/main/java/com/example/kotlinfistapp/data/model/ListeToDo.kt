package com.example.kotlinfistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinfistapp.data.model.ItemToDo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class ListeToDo(var title : String) : Serializable {
    @SerializedName("label")
    private var titreListeToDo : String = title
    private var lesItems : MutableList<ItemToDo> = mutableListOf()

    @PrimaryKey
    public var id : String =""
    // La clé primaire est l'id de la liste.

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