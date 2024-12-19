package com.example.mydoctor.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Класс данных давления
@Entity
data class DataPressure(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,                    // id - ключ
    var upperDataPressure: String,          // верхнее давление (Систолическое)
    var lowerDataPressure: String,          // нижнее давление (Диастолическое)
    var pulse: String,                      // пульс
    var dateAndTimeOfMeasurements: Date,    // дата и время измерений
    var noteOfMeasurements: String         // заметка во время измерений

)
