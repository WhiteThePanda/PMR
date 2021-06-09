package com.example.kotlinfistapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ItemToDo(var descr : String):Serializable {
    @SerializedName("label")
    private var _description : String = ""
    @SerializedName("checked")
    var faitText : String ="0"
    private var fait : Boolean = false;
    public var id:String =""
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
