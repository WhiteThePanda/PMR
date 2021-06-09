package com.example.kotlinfistapp.data.model

import com.example.kotlinfistapp.data.model.ListeToDo
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ProfilListeToDo(var name: String):Serializable {
    @SerializedName("pseudo")
    private var _login : String = ""
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