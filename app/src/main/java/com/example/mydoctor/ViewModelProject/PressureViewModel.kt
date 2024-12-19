package com.example.mydoctor.ViewModelProject

import android.util.Log
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
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

    val upperPressureState = mutableStateOf<String>("")
    val lowerPressureState = mutableStateOf<String>("")
    val pulseState = mutableStateOf<String>("")
    val dateAndTimeOfMeasurementsState = mutableStateOf<Date>(Date())
    var noteOfMeasurementsState = mutableStateOf<String>("")

    val saveButtonEnabledState = mutableStateOf<Boolean>(false)
    fun enablingSaveButton (){
        saveButtonEnabledState.value = upperPressureState.value.isNotEmpty()
                && lowerPressureState.value.isNotEmpty()
    }
    val showDatePickerDialog = mutableStateOf(false)

    fun turnOffDatePickerDialog(){
        showDatePickerDialog.value = false
    }
    fun turnOnDatePickerDialog(){
        showDatePickerDialog.value = true
    }
    val selectedDateInPickerDialogState = mutableLongStateOf(System.currentTimeMillis())


    val enabledPopupState = mutableStateOf(true)
    val snackbarVisibleState = mutableStateOf(false)
    val snackbarVisibleDateState = mutableStateOf(false)

    suspend fun delayPopup() {
        delay(10000)
        enabledPopupState.value = false
    }

    fun turnOffEnabledPopup() {
        enabledPopupState.value = false
    }

    fun turnOffSnackbarVisibleDate() {
        snackbarVisibleDateState.value = false
    }
    fun turnOffSnackbarVisible() {
        snackbarVisibleState.value = false
    }


    val snackbarHostState =  mutableStateOf(SnackbarHostState())

    fun insertData(){
        saveButtonEnabledState.value = false
        insertDataPressure(
            DataPressure(
            upperDataPressure = upperPressureState.value,
            lowerDataPressure = lowerPressureState.value,
            pulse = pulseState.value,
            dateAndTimeOfMeasurements = dateAndTimeOfMeasurementsState.value,
            noteOfMeasurements = noteOfMeasurementsState.value
            )
        )
    }

    private val displayDataPressure = mutableStateOf<List<DataPressure>>(emptyList())

    private fun getTodayDataPressures() {
        viewModelScope.launch {
            displayDataPressure.value = mainDb.dao.getDatesToday()
        }
    }

    private fun getWeekDataPressures() {
        viewModelScope.launch {
            displayDataPressure.value = mainDb.dao.getAllDates()
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


    private val dataFormatForDatePicker = SimpleDateFormat("dd.MM.yyyy")

    val currentDate = dataFormatForDatePicker.format(Date())
    var labelForDateButton = mutableStateOf<String>(currentDate)
    fun CheckDateCurrentAndAfterOrBefore() {

        val selectedDateWithFormat = dataFormatForDatePicker.format(selectedDateInPickerDialogState.longValue)
        val parseSelectedDate = dataFormatForDatePicker.parse(selectedDateWithFormat)
        val parseCurrentDate = dataFormatForDatePicker.parse(currentDate)

        if (parseCurrentDate.compareTo(parseSelectedDate) > 0) {
            snackbarVisibleState.value = true
            snackbarVisibleDateState.value = true
            labelForDateButton.value = currentDate
        } else {
            labelForDateButton.value = selectedDateWithFormat
        }
    }


    private val calendarCurrentTime: Calendar = Calendar.getInstance()
    val currentHour = mutableIntStateOf( calendarCurrentTime[Calendar.HOUR_OF_DAY])
    val currentMinute = mutableIntStateOf(calendarCurrentTime[Calendar.MINUTE])

    var showTimePickerState = mutableStateOf(false)

    fun turnOnTimePicker() {
        showTimePickerState.value = true
    }
    fun turnOffTimePicker() {
        showTimePickerState.value = false
    }


    val currentTimeForPicker = mutableStateOf(String.format("%02d:%02d",currentHour.intValue,currentMinute.intValue))
    val selectedTimeFromPicker = mutableStateOf(String.format("%02d:%02d",currentHour.intValue,currentMinute.intValue))


    val labelForTimeButton= mutableStateOf(currentTimeForPicker.value)
    fun CheckTimeCurrentAndAfterOrBefore() {
        val currentDateAndCurrentTimeDay = currentDate + " " + currentTimeForPicker.value
        val selectedDateAndSelectedTimeDay = labelForDateButton.value + " " + selectedTimeFromPicker.value
         val dateAndTimeFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")

        val parseCurrentDateAndCurrentTimeDay = dateAndTimeFormat.parse(currentDateAndCurrentTimeDay)
        val parseSelectedDataAndSelectedTimeDay = dateAndTimeFormat.parse(selectedDateAndSelectedTimeDay)

        if (parseCurrentDateAndCurrentTimeDay > parseSelectedDataAndSelectedTimeDay) {
            turnOffSnackbarVisibleDate()
            snackbarVisibleState.value = true
            labelForTimeButton.value = currentTimeForPicker.value
            dateAndTimeOfMeasurementsState.value = parseCurrentDateAndCurrentTimeDay
        } else {
            labelForTimeButton.value = selectedTimeFromPicker.value
            dateAndTimeOfMeasurementsState.value = parseSelectedDataAndSelectedTimeDay
        }
    }

    var showPickerOrInput =  mutableStateOf(true)

            // Main screen


}