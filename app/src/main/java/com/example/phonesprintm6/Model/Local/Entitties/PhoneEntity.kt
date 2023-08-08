package com.example.phonesprintm6.Model.Local.Entitties

import androidx.room.Entity
import androidx.room.PrimaryKey

// Declaración de Entidad
@Entity(tableName = "phone_table")
data class PhoneEntity(

    // Declaración de variables

    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String
)

