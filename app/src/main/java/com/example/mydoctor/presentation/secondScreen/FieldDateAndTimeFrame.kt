package com.example.mydoctor.presentation.secondScreen

import AdvancedTimePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mydoctor.R
import com.example.mydoctor.ViewModelProject.PressureViewModel
import com.example.mydoctor.ui.theme.Black


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDateAndTimeFrame(
    vm: PressureViewModel
) {

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

            vm.datePickerState.value = rememberDatePickerState()
            TextButton(
                onClick = { vm.turnOnDatePickerDialog() },
                modifier = Modifier
            ) {
                if (vm.showDatePickerDialog.value) {
                    DataPickerDialogFun(vm)
                }
                Text(
                    text = vm.labelForDateButton.value,
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


            TextButton(
                onClick = {vm.turnOnTimePicker()},
                modifier = Modifier
            ) {
                if (vm.showTimePickerState.value) {
                    AdvancedTimePicker(vm,
                        onConfirm = { timePickerState ->
                            // Обработка выбора времени
                            vm.selectedTimeFromPicker.value = String.format(
                                "%02d:%02d",
                                timePickerState.hour,
                                timePickerState.minute
                            )
                            vm.CheckTimeCurrentAndAfterOrBefore()
                            vm.turnOffTimePicker() // Закрыть диалог
                        },
                        onDismiss = {
                            vm.turnOffTimePicker() // Закрыть диалог при отмене
                        }
                    )
                }


                Text(text = vm.labelForTimeButton.value,
                    fontSize = 18.sp,
                    color = Black
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun FieldDateAndTimeFramePreview() {
    val vm = hiltViewModel<PressureViewModel>()
    FieldDateAndTimeFrame(vm)
}
