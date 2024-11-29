package com.example.mydoctor.component.secondScreen

import AdvancedTimePicker
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDateAndTimeFrame() {
    val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }
    val scope = rememberCoroutineScope()
    var selectedDate by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var dialogControllerDate by  remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()


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
                onClick = { dialogControllerDate = true },
                modifier = Modifier
            ) {
                if (dialogControllerDate) {
                    DatePickerDialog(
                        onDismissRequest = { dialogControllerDate = false },
                        confirmButton = {
                            TextButton(onClick = {
                                if (dateState.selectedDateMillis != null) {
                                    selectedDate = dateState.selectedDateMillis!!
                                }
                                dialogControllerDate = false
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
                                dialogControllerDate = false
                            }) {
                                Text(
                                    text = "Cancel",
                                    color = Black500
                                )
                            }
                        }
                    ) {
                        DatePicker(
                            modifier = Modifier
                                .padding(horizontal = 5.dp),
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

//            val selectedHour by remember { mutableIntStateOf(Calendar.getInstance().get((Calendar.HOUR_OF_DAY))) }
//            val selectedMinute by remember { mutableIntStateOf(Calendar.getInstance().get((Calendar.MINUTE))) }


            val calendar = Calendar.getInstance()
            val hour = calendar[Calendar.HOUR_OF_DAY]
            val minute = calendar[Calendar.MINUTE]


            var showTimePicker by remember { mutableStateOf(false) }
            var selectedTime by remember { mutableStateOf("Выберите время") }



            TextButton(
                onClick = {showTimePicker = true},
                modifier = Modifier
            ) {
                if (showTimePicker) {
                    AdvancedTimePicker(
                        onConfirm = { timePickerState ->
                            // Обработка выбора времени
                            selectedTime = String.format(
                                "%02d:%02d",
                                timePickerState.hour,
                                timePickerState.minute
                            )
                            showTimePicker = false // Закрыть диалог
                        },
                        onDismiss = {
                            showTimePicker = false // Закрыть диалог при отмене
                        }
                    )
                }


                Text(text = selectedTime,
                    fontSize = 18.sp,
                    color = Black
                )
            }
        }
    }
    SnackbarHost(snackbarHostState.value)
}


@Preview(showBackground = true)
@Composable
fun FieldDateAndTimeFramePreview() {
    FieldDateAndTimeFrame()
}
