package com.example.phonesprintm6.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phonesprintm6.Model.Local.Entitties.PhoneEntity
import com.example.phonesprintm6.Model.Local.Entitties.PhoneDetailEntity


@Dao
interface PhoneDao {


    // Declaración de funciones a utilizar en la BD

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhones(phoneList: List<PhoneEntity>)

    @Query("SELECT * FROM PHONE_TABLE ORDER BY id ASC")
    fun getAllPhones(): LiveData<List<PhoneEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetail(phone: PhoneDetailEntity)

    @Query("SELECT * FROM PHONE_DETAIL_TABLE WHERE id=:id")
    fun getPhoneDetailById(id: String): LiveData<PhoneDetailEntity>

}