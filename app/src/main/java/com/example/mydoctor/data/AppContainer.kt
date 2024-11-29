package com.example.mydoctor.data

import android.content.Context
interface AppContainer {
    val dataPressureRepository: DataPressureRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val dataPressureRepository: DataPressureRepository by lazy {
        OfflineDataPressureRepository(InventoryDatabase.getDatabase(context).dataPressureDao())
    }
}