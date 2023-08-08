package com.example.phonesprintm6.Model.Local.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phonesprintm6.Model.Local.Entitties.PhoneEntity
import com.example.phonesprintm6.Model.Local.PhoneDao
import com.example.phonesprintm6.Model.Local.Entitties.PhoneDetailEntity


// Creacion de BD según entidades declaradas
@Database(
    entities = [PhoneEntity::class, PhoneDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PhoneDataBase : RoomDatabase() {

    // Instancia del DAO
    abstract fun getPhoneDao(): PhoneDao

    companion object {

        @Volatile
        private var
                INSTANCE: PhoneDataBase? = null

        fun getDataBase(context: Context): PhoneDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDataBase::class.java, "phoneDb"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}