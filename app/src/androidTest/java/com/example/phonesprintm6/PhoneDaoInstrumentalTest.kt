package com.example.phonesprintm6

import android.util.Log
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.phonesprintm6.Model.Local.Database.PhoneDataBase
import com.example.phonesprintm6.Model.Local.Entitties.PhoneDetailEntity
import com.example.phonesprintm6.Model.Local.Entitties.PhoneEntity
import com.example.phonesprintm6.Model.Local.PhoneDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering.Context

//Todo Check it out
@RunWith(AndroidJUnit4::class)
//@Config(sdk=[Build.VERSION_CODES.Q], manifest= Config.NONE)
class PhoneDaoInstrumentalTest {

    private lateinit var phoneDao: PhoneDao
    private lateinit var db: PhoneDataBase

    @Before
    fun setup(){
        val context= ApplicationProvider.getApplicationContext<android.content.Context>()
        db= Room.inMemoryDatabaseBuilder(context,PhoneDataBase::class.java).build()
        phoneDao=db.getPhoneDao()
    }

    @After
    fun shutDown(){
        db.close()
    }

    @Test
    fun insertListPhones()= runBlocking {
        val phoneEntity= listOf(
            PhoneEntity(1,"pruena",1,"prueba imagen"),
            PhoneEntity(2,"prueba 2",2,"prueba imagen 2")
        )

        phoneDao.insertAllPhones(phoneEntity)
        Log.d("TEST", phoneDao.insertAllPhones(phoneEntity).toString())

        val phoneLivedata= phoneDao.getAllPhones()
        val phoneList: List<PhoneEntity> = phoneLivedata.value?: emptyList()

        Log.d("TestLogs", "Tamaño de la lista de teléfonos: ${phoneList.size}")
        Log.d("TestLogs", "Lista de teléfonos: $phoneList")

        //verificar el listado

        assertThat(phoneList, not(emptyList()))
        assertThat(phoneList.size,equalTo(2))

    }

    @Test
    fun insertPhoneDetail()= runBlocking{

        val phoneDetail= PhoneDetailEntity(
            2,
            "nombre insertado",
            2,
            "imagen2",
            "descripcion2",
            2,
            true
        )

        phoneDao.insertPhoneDetail(phoneDetail)
        val phoneLiveData= phoneDao.getPhoneDetailById("2")
        val phoneValue= phoneLiveData.value


        assertThat(phoneValue?.price, equalTo(2))
        assertThat(phoneValue?.image, equalTo("imagen2"))


    }




}