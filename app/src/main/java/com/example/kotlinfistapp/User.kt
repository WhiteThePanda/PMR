package com.example.kotlinfistapp

import java.io.Serializable


class User(var name: String):Serializable {
    private var _name : String = ""
    var toDoLists : MutableList<ListeToDo> = mutableListOf<ListeToDo>()

    init {
        _name=name
    }
    public fun GetToDo() :MutableList<ListeToDo>{
        return toDoLists
    }
    override fun toString(): String {
        return "User(_name='$_name', toDoList=$toDoLists)"
    }

}