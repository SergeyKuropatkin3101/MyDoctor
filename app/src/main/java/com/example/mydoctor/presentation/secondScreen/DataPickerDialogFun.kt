package com.example.mydoctor.presentation.secondScreen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
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
    vm: PressureViewModel
) {

    val dateState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = { vm.dialogControllerDate.value = false },
        confirmButton = {
            TextButton(onClick = {
                if (dateState.selectedDateMillis != null) {
                    vm.selectedDate.value = dateState.selectedDateMillis!!
                    Log.i("dsf", vm.selectedDate.value.toString())



                }
                vm.dialogControllerDate.value = false
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
                vm.dialogControllerDate.value = false
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