package com.example.kotlinfistapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ProfilListeToDo(var name: String):Serializable {
    @SerializedName("user")
    private var _login : String = ""
    @SerializedName("lists")
    private var mesListesToDo : MutableList<ListeToDo> = mutableListOf<ListeToDo>()

    init {
        _login=name
    }
    public fun GetMesListesToDo() :MutableList<ListeToDo>{
        return mesListesToDo
    }
    override fun toString(): String {
        return "User(_name='$_login', toDoList=$mesListesToDo)"
    }

}