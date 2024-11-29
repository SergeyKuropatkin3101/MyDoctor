package com.example.mydoctor.data

interface DataPressureRepository {

    fun getDatesInLastMonthStream(startData: Long): List<DataPressure>

    fun getDatesInLastWeekStream(startData: Long): List<DataPressure>


    fun getTodayStream(): List<DataPressure?>


    suspend fun insertDataPressure(dataPressure: DataPressure)


}