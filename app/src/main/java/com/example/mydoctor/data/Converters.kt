package com.example.mydoctor.data

import androidx.room.TypeConverter
import java.util.Date

// Класс для сохранения данных (типа Date) с помощью Room (поддерживает Long)
class Converters {
    //    Функция преобразования из Long в Date
    @TypeConverter
    fun fromTimestamp(value: Long): Date? {
        return value?.let { Date(it) }
    }

    //    Функция преобразования из Date s Long
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}