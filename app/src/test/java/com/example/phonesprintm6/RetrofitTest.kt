package com.example.phonesprintm6

import com.example.phonesprintm6.Model.Remote.RetrofitClient
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitTest {

    private lateinit var mockWebServer: MockWebServer


    //primero el mock server
    @Before
    fun setup(){
        mockWebServer = MockWebServer()
    }

    //apagamos el mock server
    @After
    fun tearDown(){

        mockWebServer.shutdown()
    }

    //hacemos la prueba
    @Test
    fun testRetrofit(){

        //entregamos una url base simulada
        val expectedBaseUrl = mockWebServer.url("agua/").toString()

        val retrofit = Retrofit.Builder()
            .baseUrl(expectedBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // asignar la instancia real de retofit a la propiedad retrofitIntance de Retrofitcliente
        //usamos retrofit para pasar nuestra conexion simulada
        RetrofitClient.retrofitInstance = retrofit
        val retrofitInstance = RetrofitClient.retrofitInstance
        assertEquals(expectedBaseUrl, retrofitInstance.baseUrl().toString())
    }
}