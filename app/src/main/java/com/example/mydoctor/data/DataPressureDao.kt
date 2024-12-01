package com.example.mydoctor.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataPressureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataPressure(dataPressure: DataPressure)

/*
    @Query("SELECT * FROM DataPressure WHERE dateOfMeasurements >= :startDate")
    fun getDatesInLastMonth(startDate: Long): List<DataPressure>

    @Query("SELECT * FROM DataPressure WHERE dateOfMeasurements >= :startDate")
    fun getDatesInLastWeek(startDate: Long): List<DataPressure>
*/

    @Query("SELECT * FROM DataPressure WHERE dateOfMeasurements >= 0")
    fun getDatesToday(): List<DataPressure>

    @Delete
    suspend fun deleteDataPressure(dataPressure: DataPressure)

}