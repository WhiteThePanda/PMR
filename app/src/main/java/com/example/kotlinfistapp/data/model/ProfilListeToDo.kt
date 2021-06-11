package com.example.kotlinfistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinfistapp.data.model.ListeToDo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class ProfilListeToDo(@SerializedName("pseudo")
                           @PrimaryKey
                           val _login : String,
                           val mdp : String
)