package com.example.mydoctor.component.secondScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.Black500
import com.example.mydoctor.ui.theme.Blue
import com.example.mydoctor.ui.theme.White
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDateAndTimeFrame(modifier: Modifier = Modifier) {
    val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }
    val scope = rememberCoroutineScope()
    var selectedDate by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var dateDialogController by  remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()

    var dateDialogControllerTime by  remember { mutableStateOf(false) }


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        Column {
            Text(
                text = stringResource(R.string.textDataMeasurement),
                fontSize = 16.sp
            )

            val dataFormat = SimpleDateFormat(
                "dd.MM.yyyy"
            )
            val currentDate = dataFormat.format(Date())

            TextButton(
                onClick = { dateDialogController = true },
                modifier = Modifier
            ) {
                if (dateDialogController) {
                    DatePickerDialog(
                        onDismissRequest = { dateDialogController = false },
                        confirmButton = {
                            TextButton(onClick = {
                                if (dateState.selectedDateMillis != null) {
                                    selectedDate = dateState.selectedDateMillis!!
                                }
                                dateDialogController = false
                            }
                            ) {
                                Text(
                                    text = "Ok",
                                    color = Blue
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                dateDialogController = false
                            }) {
                                Text(
                                    text = "Cancel",
                                    color = Black500
                                )
                            }
                        }
                    ) {
                        DatePicker(
                            state = dateState,
                            colors = DatePickerDefaults.colors(
                                todayContentColor = Blue,
                                todayDateBorderColor = Blue,
                                dayContentColor = Black1000,
                                selectedDayContainerColor = Blue,
                                selectedDayContentColor = White,
                            )
                        )
                    }
                }

                val selectedDateWithFormat = dataFormat.format(selectedDate)
                var textDate = currentDate

                if (currentDate.compareTo(selectedDateWithFormat) > 0) {

                    val textIncorrectData = stringResource(
                        id = R.string.textIncorrectData
                    )
                    scope.launch {
                        snackbarHostState.value.showSnackbar(
                            textIncorrectData
                        )
                    }


                } else {
                    textDate = selectedDateWithFormat
                }

                Text(
                    text = textDate,
                    fontSize = 18.sp,
                    color = Black,
                )
            }
        }
        Column {
            Text(
                text = stringResource(R.string.textTimeMeasurement),
                fontSize = 16.sp
            )

//            val timeState = rememberTimePickerState(
//                Calendar.getInstance().get((Calendar.HOUR_OF_DAY)),
//                Calendar.getInstance().get((Calendar.MINUTE)),
//                true
//            )

            val timeFormat = SimpleDateFormat("HH:mm")
            val currentTime = timeFormat.format(Date())
            var selectedHour by remember { mutableIntStateOf(Calendar.getInstance().get((Calendar.HOUR_OF_DAY))) }
            var selectedMinute by remember { mutableIntStateOf(Calendar.getInstance().get((Calendar.MINUTE))) }

            val timeState = rememberTimePickerState(
                initialHour = selectedHour,
                initialMinute = selectedMinute
            )
            var selectedTime = rememberTimePickerState(
                initialHour = selectedHour,
                initialMinute = selectedMinute
            )
            var showDialog by remember { mutableStateOf(false) }
            TextButton(
                onClick = {
                    dateDialogControllerTime = true
                    showDialog = true
                },
                modifier = Modifier
            ) {

                if(dateDialogControllerTime == true){
                    selectedTime = TimePickerWithDialog()
                    dateDialogControllerTime = false
                }
                var selTime = selectedTime.hour.toString()+":"+selectedTime.minute.toString()



                Text(text = selTime,
                    fontSize = 18.sp,
                    color = Black
                )
            }
        }
    }
    SnackbarHost(snackbarHostState.value)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerWithDialog(): TimePickerState
 {
    var selectedHour by remember { mutableIntStateOf(Calendar.getInstance().get((Calendar.HOUR_OF_DAY))) }
    var selectedMinute by remember { mutableIntStateOf(Calendar.getInstance().get((Calendar.MINUTE))) }
    val timeState = rememberTimePickerState(
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = { showDialog = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.LightGray.copy(alpha = .3f))
                    .padding(top = 28.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(state = timeState)
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Dismiss")
                    }
                    TextButton(onClick = {
                        showDialog = false
                        selectedHour = timeState.hour
                        selectedMinute = timeState.minute
                    }) {
                        Text(text = "Confirm")
                    }
                }
            }
        }
    }
    showDialog = true
    return timeState
}

@Preview(showBackground = true)
@Composable
fun FieldDateAndTimeFramePreview() {
    FieldDateAndTimeFrame()
}
