package com.example.mydoctor.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [DataPressure::class], version = 1)
abstract class MainDb : RoomDatabase(){
    abstract val dao: DataPressureDao
}