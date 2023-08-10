package com.example.phonesprintm6

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.phonesprintm6.View.MainActivity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DetailInstrumentalTestActivity {

    //esta prueba simula un intent que envia datos a la main activity

    @Test
    fun testShowValidatePhone(){

        //creamos contexto

        val context= androidx.test.platform.app.InstrumentationRegistry.getInstrumentation()
            .targetContext

        //creamos el intent que va de este contexto a la main activity

        val intent= Intent(context, MainActivity::class.java)

        //enviamos los datos con key value

        intent.putExtra("id",1)
        intent.putExtra("name","phone prueba")
        intent.putExtra("price",1)
        intent.putExtra("image","test image")
        intent.putExtra("description","test description")
        intent.putExtra("lastPrice",1)
        intent.putExtra("credit",true)

        //lanzamos la mainactivity de prueba con el intent simulado

        val activityScenario=ActivityScenario.launch<MainActivity>(intent)

        //una vez lanzada la actividad la declaramos y empezamos a revisar lo recibido en el intent

        activityScenario.onActivity { activity->

            //primero ver que no sea null
            assertNotNull(activity)

            //revisar que lo enviado sea recibido en la actividad

            assertEquals(1,activity.intent.getIntExtra("id",-1))
            assertEquals("phone prueba",activity.intent.getStringExtra("name",))
            assertEquals(1,activity.intent.getIntExtra("price",-1))
            assertEquals("test image",activity.intent.getStringExtra("image",))
            assertEquals("test description",activity.intent.getStringExtra("description",))
            assertEquals(1,activity.intent.getIntExtra("lastPrice",-1))
            assertEquals(true,activity.intent.getBooleanExtra("credit",false))

        }
    }
}