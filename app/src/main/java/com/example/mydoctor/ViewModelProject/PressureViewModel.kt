package com.example.mydoctor.ViewModelProject

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydoctor.data.DataPressure
import com.example.mydoctor.data.MainDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PressureViewModel @Inject constructor (
    val mainDb: MainDb
) : ViewModel() {

    val valueUpperPressure = mutableStateOf<String>("")
    val valueLowerPressure = mutableStateOf<String>("")
    var saveButtonEnabled = mutableStateOf<Boolean>(false)

    var valuePulse = mutableStateOf<String>("")
    var valueDateOfMeasurements = mutableStateOf<Long>(0L)

    var valueTimeOfMeasurements = mutableStateOf<Long>(0L)
    var valueNoteOfMeasurements = mutableStateOf<String>("")

    fun isSaveButtonEnabled (){
        saveButtonEnabled.value = valueUpperPressure.value.isNotEmpty()
                && valueLowerPressure.value.isNotEmpty()
    }
    var dialogControllerDate = mutableStateOf(false)
    var selectedDate = mutableLongStateOf(System.currentTimeMillis())

    val snackbarHostState =  mutableStateOf(SnackbarHostState())

    fun insertData(){
        saveButtonEnabled.value = false
        Log.i("tag",
            valuePulse.value.toIntOrNull().toString()
        )
        insertDataPressure(
            DataPressure(
            upperDataPressure = valueUpperPressure.value,
            lowerDataPressure = valueLowerPressure.value,
            pulse = valuePulse.value,
            dateOfMeasurements = valueDateOfMeasurements.value,
            timeOfMeasurements = valueTimeOfMeasurements.value,
            noteOfMeasurements = valueNoteOfMeasurements.value
            )
        )
    }

    val DisplayDataPressure = mutableStateOf<List<DataPressure>>(emptyList())

    private fun getTodayDataPressures() {
        viewModelScope.launch {
            DisplayDataPressure.value = mainDb.dao.getDatesToday()
        }
    }

    fun insertDataPressure(dataPressure: DataPressure) = viewModelScope.launch {
        mainDb.dao.insertDataPressure(dataPressure)
    }
    fun deleteDataPressure(dataPressure: DataPressure) = viewModelScope.launch {
        mainDb.dao.deleteDataPressure(dataPressure)
    }
}