package com.example.mydoctor.presentation.secondScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mydoctor.ViewModelProject.PressureViewModel
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.Black500
import com.example.mydoctor.ui.theme.Blue
import com.example.mydoctor.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataPickerDialogFun(
    vm: PressureViewModel,
) {

    DatePickerDialog(
        onDismissRequest = { vm.turnOffDatePickerDialog() },
        confirmButton = {
            TextButton(onClick = {
                if (vm.datePickerState.value!!.selectedDateMillis != null) {
                    vm.selectedDateInPickerDialogState.longValue = vm.datePickerState.value!!.selectedDateMillis!!
                }
                vm.CheckDateCurrentAndAfterOrBefore()
                vm.turnOffDatePickerDialog()
            }) {
                Text(
                    text = "Ok",
                    color = Blue
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                vm.turnOffDatePickerDialog()
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
            state = vm.datePickerState.value!!,
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