package com.example.phonesprintm6

import android.util.Log
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.phonesprintm6.Model.Local.Database.PhoneDataBase
import com.example.phonesprintm6.Model.Local.Entitties.PhoneDetailEntity
import com.example.phonesprintm6.Model.Local.Entitties.PhoneEntity
import com.example.phonesprintm6.Model.Local.PhoneDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering.Context
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.jvm.Throws


@RunWith(AndroidJUnit4::class)
//@Config(sdk=[Build.VERSION_CODES.Q], manifest= Config.NONE)
class PhoneDaoInstrumentalTest {

    //la regla dice que haremos las pruebas en el hilo principal
    @get:Rule val instantTaskExecutorRule= InstantTaskExecutorRule()

    //private lateinit var phoneDao: PhoneDao
    private lateinit var db: PhoneDataBase

    @Before
    fun setupDB() {
        //setteamos la base de datos antes del test
        db= Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            PhoneDataBase::class.java).build()
    }

    @After
    @Throws(IOException::class)
    fun shutDown(){
        //despues del test apagamos la base de datos
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun coroutineDBInsertPhone(){
        //primer test instanciamos el dao

        val phoneDao= db.getPhoneDao()

        //creamos 2 phone entity

        val phone1= PhoneEntity(1,"pruena",1,"prueba imagen")
        val phone2= PhoneEntity(2,"prueba 2",2,"prueba imagen 2")
        val phones= listOf(phone1,phone2)

        //en la coroutine

        runBlocking(Dispatchers.Default) {
            //insertamos phone 1 y2(phones)
            phoneDao.insertAllPhones(phones)
        }

        phoneDao.getAllPhones().observeForever{
            //observamos el livedata y comprobamos que su tamaño sea 2
            assertThat(phones.size, equalTo(2))
        }





    }



    @ExperimentalCoroutinesApi
    @Test
    @Throws(Exception::class)
    fun insertPhoneDetail() {
        //acá probaremos la otra funcion del dao
        val phoneDao = db.getPhoneDao()

        val phoneDetail = PhoneDetailEntity(
            2,
            "nombre insertado",
            2,
            "imagen2",
            "descripcion2",
            2,
            true
        )

        //variable para manejar coroutine en ambiente de prueba
        val testDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testDispatcher)

        testDispatcher.runBlockingTest {
            phoneDao.insertPhoneDetail(phoneDetail)
            val phoneLiveData = phoneDao.getPhoneDetailById("2")
            val phoneValue = phoneLiveData.getOrAwaitValue()

            phoneDao.getPhoneDetailById("2")
                assertThat(phoneValue?.price, equalTo(2))
                assertThat(phoneValue?.image, equalTo("imagen2"))

        }
        Dispatchers.resetMain()

    }

    //funcion auxiliar obtiene o espera valor de un livedata
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        //el observer captura el valor del livedata
        val observer = object : Observer<T> {
            override fun onChanged(o: T) {
                data = o
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        afterObserve.invoke()

        // Si no se obtiene el valor en el tiempo esperado lanza una excepcion
        if (!latch.await(time, timeUnit)) {
            this.removeObserver(observer)
            throw TimeoutException("El valor del LiveData no se obtuvo en el tiempo esperado.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}