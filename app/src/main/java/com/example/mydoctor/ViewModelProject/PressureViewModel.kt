package com.example.mydoctor.ViewModelProject

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydoctor.data.DataPressure
import com.example.mydoctor.data.MainDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PressureViewModel @Inject constructor (
    val mainDb: MainDb
) : ViewModel() {

    val valueUpperPressure = mutableStateOf<String>("")
    val valueLowerPressure = mutableStateOf<String>("")
    var saveButtonEnabled = mutableStateOf<Boolean>(false)

    var valuePulse = mutableStateOf<String>("")
    var valueDateOfMeasurements = mutableStateOf<Date>(Date())

    var valueTimeOfMeasurements = mutableStateOf<Date>(Date())
    var valueNoteOfMeasurements = mutableStateOf<String>("")

    fun isSaveButtonEnabled (){
        saveButtonEnabled.value = valueUpperPressure.value.isNotEmpty()
                && valueLowerPressure.value.isNotEmpty()
    }
    var dialogControllerDate = mutableStateOf(false)
    var selectedDate = mutableLongStateOf(System.currentTimeMillis())


    var enabledTooltip = mutableStateOf(true)
    var snackbarHostVisible = mutableStateOf(false)

    suspend fun delayTooltip() {
        delay(10000)
        enabledTooltip.value = false
    }

    var snackbarHostState =  mutableStateOf(SnackbarHostState())

    fun insertData(){
        saveButtonEnabled.value = false
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

    private val displayDataPressure = mutableStateOf<List<DataPressure>>(emptyList())

    private fun getTodayDataPressures() {
        viewModelScope.launch {
            displayDataPressure.value = mainDb.dao.getDatesToday()
        }
    }

    private fun insertDataPressure(dataPressure: DataPressure) = viewModelScope.launch {
        mainDb.dao.insertDataPressure(dataPressure)
    }
    fun deleteDataPressure(dataPressure: DataPressure) = viewModelScope.launch {
        mainDb.dao.deleteDataPressure(dataPressure)
    }
    @OptIn(ExperimentalMaterial3Api::class)
    val datePickerState = mutableStateOf<DatePickerState?>(null)


    private val dataFormat = SimpleDateFormat(
        "dd.MM.yyyy"
    )


    val currentDate = dataFormat.format(Date())
    var textDate = mutableStateOf<String>(currentDate)
    fun isCorrectDate() {

        val selectedDateWithFormat = dataFormat.format(selectedDate.longValue)
        val startDate = dataFormat.parse(selectedDateWithFormat)
        val startDateC = dataFormat.parse(currentDate)

        if (startDateC.compareTo(startDate) > 0) {
            snackbarHostVisible.value = true
            textDate.value = currentDate
            valueDateOfMeasurements.value = startDateC
        } else {
            textDate.value = selectedDateWithFormat
            valueDateOfMeasurements.value = startDate
        }
    }


    private val calendarCurrent: Calendar = Calendar.getInstance()
    val hour = mutableIntStateOf( calendarCurrent[Calendar.HOUR_OF_DAY])
    val minute = mutableIntStateOf(calendarCurrent[Calendar.MINUTE])

    var showTimePicker = mutableStateOf(false)
    var selectedTime = mutableStateOf(String.format("%02d:%02d",hour.value,minute.value))


    /*fun isCorrectTime() {

         val dataFormatWithHourAndMinute = SimpleDateFormat(
        "dd.MM.yyyy HH:mm"
         )

        val selectedTimWithFormat = dataFormat.format(selectedDate.longValue)
        val startDate = dataFormat.parse(selectedDateWithFormat)
        val startDateC = dataFormat.parse(currentDate)

        if (startDateC.compareTo(startDate) > 0) {
            snackbarHostVisible.value = true
            textDate.value = currentDate
            valueDateOfMeasurements.value = startDateC
        } else {
            textDate.value = selectedDateWithFormat
            valueDateOfMeasurements.value = startDate
        }
    }*/

    /** Determines whether the time picker is dial or input */
    var showDial =  mutableStateOf(true)


}