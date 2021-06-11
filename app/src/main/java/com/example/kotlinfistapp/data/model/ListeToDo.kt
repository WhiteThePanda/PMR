package com.example.kotlinfistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinfistapp.data.model.ItemToDo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class ListeToDo(
        @PrimaryKey
        val id : String ,
        @SerializedName("label")
        val titreListeToDo : String,
        var login : String)