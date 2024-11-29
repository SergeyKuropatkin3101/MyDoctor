package com.example.mydoctor.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataPressure::class], version = 1, exportSchema = false)
abstract class InventoryDatabase: RoomDatabase()  {
    abstract fun dataPressureDao(): DataPressureDao
    companion object {
        @Volatile
        private var Instance:InventoryDatabase? = null
        fun getDatabase(context: Context): InventoryDatabase{
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    InventoryDatabase::class.java,
                    "dataPressure"
                ).build()
                    .also{ Instance = it}
            }

        }
    }
}