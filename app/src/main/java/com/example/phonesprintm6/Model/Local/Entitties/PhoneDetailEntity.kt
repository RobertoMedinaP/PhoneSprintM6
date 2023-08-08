package com.example.phonesprintm6.Model.Local.Entitties

import androidx.room.Entity
import androidx.room.PrimaryKey


// Declaración de Entidad
@Entity(tableName = "phone_detail_table")
data class PhoneDetailEntity(

    // Declaración de variables
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)