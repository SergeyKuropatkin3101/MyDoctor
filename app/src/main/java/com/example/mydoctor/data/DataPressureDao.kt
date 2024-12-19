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

 /*   //    Функция получения данных по дате и времени за месяц
    @Query("SELECT * FROM DataPressure WHERE dateAndTimeOfMeasurements >= :startDate")
    fun getDatesInLastMonth(startDate: Long): List<DataPressure>
*/
    //    Функция получения данных по дате и времени за неделю
    @Query("SELECT * FROM DataPressure")
    suspend fun getAllDates(): List<DataPressure>

    //    Функция получения данных по дате и времени за день
    @Query("SELECT * FROM DataPressure WHERE dateAndTimeOfMeasurements >= 0")
    suspend fun getDatesToday(): List<DataPressure>

    //    Функция удаления данных
    @Delete
    suspend fun deleteDataPressure(dataPressure: DataPressure)

}