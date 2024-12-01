package com.example.mydoctor.presentation.secondScreen

import AdvancedTimePicker
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mydoctor.R
import com.example.mydoctor.ViewModelProject.PressureViewModel
import com.example.mydoctor.ui.theme.Black
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDateAndTimeFrame(
    vm: PressureViewModel
) {

    val scope = rememberCoroutineScope()


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
            var textDate = currentDate
            TextButton(
                onClick = { vm.dialogControllerDate.value = true },
                modifier = Modifier
            ) {
                if (vm.dialogControllerDate.value) {
                    DataPickerDialogFun(vm)

                    val selectedDateWithFormat = dataFormat.format(vm.selectedDate.value)


                    if (currentDate > selectedDateWithFormat) {
                        Log.i("l", (currentDate > selectedDateWithFormat).toString())

                        val textIncorrectData = stringResource(
                            id = R.string.textIncorrectData
                        )
//                    LaunchedEffect(key1 = Unit) {
//                        scope.launch() {
//                            vm.snackbarHostState.value.showSnackbar(
//                                textIncorrectData
//                            )
//                        }
//                    }


                    } else {
                        textDate = selectedDateWithFormat
                    }
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
    SnackbarHost(vm.snackbarHostState.value)
}


@Preview(showBackground = true)
@Composable
fun FieldDateAndTimeFramePreview() {
    val vm = hiltViewModel<PressureViewModel>()
    FieldDateAndTimeFrame(vm)
}
