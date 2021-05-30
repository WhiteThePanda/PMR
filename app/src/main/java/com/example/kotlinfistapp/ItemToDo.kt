package com.example.kotlinfistapp

import java.io.Serializable

class ItemToDo(var description : String):Serializable {
    var _description : String = ""
    var fait : Boolean = false;
    init {
        _description = description
    }
}
