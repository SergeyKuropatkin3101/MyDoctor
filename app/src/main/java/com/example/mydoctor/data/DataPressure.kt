package com.example.mydoctor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataPressure (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var upperDataPressure: Int = 0,
    var lowerDataPressure: Int = 0,
    var pulse: Int,
    var dateOfMeasurements: Long,

    var timeOfMeasurements: Long,
    var noteOfMeasurements: String = ""

)