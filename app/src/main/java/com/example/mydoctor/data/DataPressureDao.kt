package com.example.mydoctor.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataPressureDao {

    //    Функция добавления данных в базу данных
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataPressure(dataPressure: DataPressure)


    //    Функция получения всех данных в бд
    @Query("SELECT * FROM DataPressure")
    suspend fun getAllDates(): List<DataPressure>

    //    Функция получения данных по дате и времени за день
    @Query("SELECT * FROM DataPressure WHERE dateAndTimeOfMeasurements BETWEEN :startDate AND (:startDate + 86400000)")
    suspend fun getDatesDay(startDate: Long): List<DataPressure>


    //    Функция удаления данных
    @Delete
    suspend fun deleteDataPressure(dataPressure: DataPressure)

}