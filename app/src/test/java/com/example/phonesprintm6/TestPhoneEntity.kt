package com.example.phonesprintm6

import com.example.phonesprintm6.Model.Local.Entitties.PhoneEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestPhoneEntity {

    private lateinit var phoneEntity: PhoneEntity

    @Before
    fun setup(){

        phoneEntity= PhoneEntity(
            id = 2,
            name = "prueba unitaria",
            price = 1,
            image = "probando la clase phone"
        )
    }

    @After
    fun tearDown(){
        //después aprenderé a hacer esto
    }

    //acá viene la prueba
    @Test
    fun testPhone(){
        assert(phoneEntity.id==2)
        assert(phoneEntity.name=="prueba unitaria")
        assert(phoneEntity.price==1)
        assert(phoneEntity.image=="probando la clase phone")
    }
}