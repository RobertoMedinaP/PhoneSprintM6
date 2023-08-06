package com.example.phonesprintm6.Model.Local.Entitties

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "phone_detail_table")
data class PhoneDetailEntity (

    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
        )