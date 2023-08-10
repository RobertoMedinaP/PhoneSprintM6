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
    fun setup() {

        phoneEntity = PhoneEntity(

            id = 1,
            name = "Prueba Unitaria",
            price = 0,
            image = "image"
        )
    }


    @After
    fun tearDown() {
        // acciones de limpieza y liberacion de resursos
    }


    @Test
    fun testPhoneConstructor() {

        // verificar que los valores asignados en el constructor sean correcto

        assert(phoneEntity.id == 1)
        assert(phoneEntity.name == "Prueba Unitaria")
        assert(phoneEntity.price == 0)
        assert(phoneEntity.image == "image")
    }
}
