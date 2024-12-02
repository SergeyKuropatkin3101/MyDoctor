package com.example.mydoctor.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DataPressure::class], version = 1)
@TypeConverters(Converters::class)
abstract class MainDb : RoomDatabase(){
    abstract val dao: DataPressureDao
}