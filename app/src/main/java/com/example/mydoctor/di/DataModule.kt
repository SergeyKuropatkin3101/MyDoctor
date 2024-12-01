package com.example.mydoctor.di

import android.app.Application
import androidx.room.Room
import com.example.mydoctor.data.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMainDb(app: Application):MainDb{
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
        "tableDataPressure"
        ).build()
    }
}

/*    fun getTodayStream(): List<DataPressure?>


    suspend fun insertDataPressure(dataPressure: DataPressure)
*/