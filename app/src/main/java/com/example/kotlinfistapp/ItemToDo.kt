package com.example.kotlinfistapp

import java.io.Serializable

class ItemToDo(var descr : String):Serializable {
    private var _description : String = ""
    private var fait : Boolean = false;
    init {
        _description = descr
    }
    public fun getFait(): Boolean{
        return fait
    }
    public fun getDescription(): String{
        return _description
    }
    public fun setFait( value : Boolean)
    {
        fait= value
    }

}
