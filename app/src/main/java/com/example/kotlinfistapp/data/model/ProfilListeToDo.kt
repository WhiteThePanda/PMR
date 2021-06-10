package com.example.kotlinfistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinfistapp.data.model.ListeToDo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class ProfilListeToDo(var name: String):Serializable {
    @SerializedName("pseudo")
    @PrimaryKey
    private var _login : String = ""
    // Le login est unique.

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