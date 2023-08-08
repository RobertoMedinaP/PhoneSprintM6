package com.example.phonesprintm6.Model.Remote.FromInet


// Definicion de Data class
data class PhoneDetail(

    // Declaración de variables

    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String,
    val lastPrice: Int,
    val credit: Boolean
)