package com.example.mydoctor.presentation.secondScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mydoctor.R
import com.example.mydoctor.ViewModelProject.PressureViewModel
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.PlaceHolderColor
import com.example.mydoctor.ui.theme.White


@Composable
fun AddDataNotes(
    vm: PressureViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ){
        Row {
            Text(
                text = stringResource(R.string.textNote),
                fontSize = 16.sp,
                color = Black1000,
                modifier = Modifier
                    .padding(
                        16.dp,
                        6.dp
                    )

            )


        }

        val textPlaceHolder = stringResource(R.string.descriptionCondition)
        OutlinedTextField(
            value = vm.noteOfMeasurementsState.value,
            onValueChange = {vm.noteOfMeasurementsState.value = it},
            placeholder = {
                Text(
                    textPlaceHolder,
                color = PlaceHolderColor,
                fontSize =  18.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor= Black1000,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = White,
                unfocusedContainerColor = White
            ),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .padding(
                    16.dp,
                    8.dp,
                    16.dp,
                    16.dp
                )
                .fillMaxWidth()
        )
    }
}



@Preview(showBackground = true)
@Composable
fun AddDataNotesPreview() {
    val vm = hiltViewModel<PressureViewModel>()
    AddDataNotes(vm)
}

