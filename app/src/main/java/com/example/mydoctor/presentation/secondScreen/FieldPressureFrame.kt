package com.example.mydoctor.presentation.secondScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
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
fun FieldPressureFrame(
    vm: PressureViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(
                16.dp,
                24.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Column(
            modifier = Modifier
                .weight(2f)

        ) {
            Text(text = stringResource(R.string.textBloodPressure),
                modifier = Modifier
                    .padding(bottom = 8.dp))
            Row(
                modifier = Modifier
            ){
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.upperPressure),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        vm.valueUpperPressure.value,
                        {
                            vm.valueUpperPressure.value = it
                            vm.isSaveButtonEnabled()
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        placeholder = {
                            Text(
                                "120",
                                color = PlaceHolderColor,
                                fontSize =  18.sp
                            )
                        },
                        textStyle = TextStyle(fontSize =  18.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = White,
                            focusedBorderColor= Black1000,
                            unfocusedBorderColor = Color.Transparent
                        ),
                    )
                }
                Column(
                modifier = Modifier
                    .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.lowerPressure),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        vm.valueLowerPressure.value,
                        {vm.valueLowerPressure.value = it
                            vm.isSaveButtonEnabled()},
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        placeholder = { Text(
                            "90",
                            color = PlaceHolderColor,
                            fontSize =  18.sp
                        )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        textStyle = TextStyle(fontSize =  18.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = White,
                            focusedBorderColor= Black1000,
                            unfocusedBorderColor = Color.Transparent
                        ),
                    )
                }
            }
        }


        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.textPulse),
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                vm.valuePulse.value,
                {vm.valuePulse.value = it},
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                placeholder = { Text(
                    "70",
                    color = PlaceHolderColor,
                    fontSize =  18.sp
                )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle(fontSize =  18.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = White,
                    focusedBorderColor= Black1000,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FieldPressureFramePreview() {
    val vm = hiltViewModel<PressureViewModel>()
    FieldPressureFrame(vm)
}
