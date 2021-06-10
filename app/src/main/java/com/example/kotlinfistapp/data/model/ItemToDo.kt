package com.example.kotlinfistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity
data class ItemToDo(var descr : String):Serializable {
    @SerializedName("label")
    private var _description : String = ""
    @SerializedName("checked")
    var faitText : String ="0"
    private var fait : Boolean = false;

    @PrimaryKey
    public var id:String =""
    // La clé primaire est l'id unique de l'item.

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

    public fun switchCheck(checked : String){
        faitText = checked
        fait = !fait
    }

}
