package com.example.phonesprintm6.Model

import com.example.phonesprintm6.Model.Local.Entitties.PhoneDetailEntity
import com.example.phonesprintm6.Model.Local.Entitties.PhoneEntity
import com.example.phonesprintm6.Model.Remote.FromInet.Phone
import com.example.phonesprintm6.Model.Remote.FromInet.PhoneDetail

// Declaracion de funciones que unen elementos remotos con elementos locales
fun fromInternetPhoneEntity(phoneList: List<Phone>): List<PhoneEntity> {

    return phoneList.map {
        PhoneEntity(
            id = it.id,
            name = it.name,
            price = it.price,
            image = it.image
        )
    }
}

fun fromInternetPhoneDetailEntity(phone: PhoneDetail): PhoneDetailEntity {

    return PhoneDetailEntity(
        id = phone.id,
        name = phone.name,
        price = phone.price,
        image = phone.image,
        description = phone.description,
        lastPrice = phone.lastPrice,
        credit = phone.credit
    )
}