package com.example.mydoctor.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataPressureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dataPressure: DataPressure)

    @Query("SELECT * FROM DataPressure WHERE dateOfMeasurements >= :startDate")
    fun getDatesInLastMonth(startDate: Long): List<DataPressure>

    @Query("SELECT * FROM DataPressure WHERE dateOfMeasurements >= :startDate")
    fun getDatesInLastWeek(startDate: Long): List<DataPressure>

    @Query("SELECT * FROM DataPressure WHERE dateOfMeasurements >= 0")
    fun getDatesToday(): List<DataPressure>

}