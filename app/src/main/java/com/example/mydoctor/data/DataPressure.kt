package com.example.mydoctor.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class DataPressure(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var upperDataPressure: String,
    var lowerDataPressure: String,
    var pulse: String,
    var dateOfMeasurements: Date,
    var timeOfMeasurements: Date,
    var noteOfMeasurements: String?

)
