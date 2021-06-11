package com.example.kotlinfistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity
data class ItemToDo(
        @PrimaryKey
        val id:String,
        @SerializedName("label")
        val _description : String,
        @SerializedName("checked")
        var faitText : String,
        var idList:String
)
