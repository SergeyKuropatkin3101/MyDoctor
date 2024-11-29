package com.example.mydoctor.data

class OfflineDataPressureRepository(private val dataPressureDao: DataPressureDao): DataPressureRepository {
    override fun getDatesInLastMonthStream(startData: Long): List<DataPressure> = dataPressureDao.getDatesInLastMonth(startData)
    override fun getDatesInLastWeekStream(startDate: Long): List<DataPressure> = dataPressureDao.getDatesInLastWeek(startDate)
    override fun getTodayStream(): List<DataPressure> = dataPressureDao.getDatesToday()

    override suspend fun insertDataPressure(dataPressure: DataPressure) = dataPressureDao.insert(dataPressure)
}