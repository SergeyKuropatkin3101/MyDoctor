package com.example.mydoctor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataPressure(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var upperDataPressure: String,
    var lowerDataPressure: String,
    var pulse: String,
    var dateOfMeasurements: Long,
    var timeOfMeasurements: Long,
    var noteOfMeasurements: String?

)