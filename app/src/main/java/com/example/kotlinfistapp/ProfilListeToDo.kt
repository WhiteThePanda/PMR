package com.example.kotlinfistapp

import java.io.Serializable


class ProfilListeToDo(var name: String):Serializable {
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