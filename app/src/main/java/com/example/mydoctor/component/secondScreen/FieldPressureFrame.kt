package com.example.mydoctor.component.secondScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R

@Composable
fun FieldPressureFrame(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                16.dp,
                24.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Column(
            modifier = Modifier
                .weight(2f)

        ) {
            Text(text = stringResource(R.string.textBloodPressure),
                modifier = Modifier
                    .padding(bottom = 8.dp))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly
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
                    val message = remember{mutableStateOf("")}
                    OutlinedTextField(
                        message.value,
                        {message.value = it},
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        placeholder = { Text("120") },
                        textStyle = TextStyle(fontSize =  18.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor= Color(0xff16a085), // цвет при получении фокуса
                            unfocusedBorderColor = Color(0xffcccccc)  // цвет при отсутствии фокуса
                        ),
                        modifier = Modifier
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
                    val message = remember{mutableStateOf("")}
                    OutlinedTextField(
                        message.value,
                        {message.value = it},
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        placeholder = { Text("90") },
                        textStyle = TextStyle(fontSize =  18.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor= Color(0xff16a085),
                            unfocusedBorderColor = Color(0xffcccccc)
                        ),
                        modifier = Modifier

                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(24.dp))


        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(R.string.textPulse),
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))
            val message = remember{mutableStateOf("")}
            OutlinedTextField(
                message.value,
                {message.value = it},
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                placeholder = { Text("70") },
                textStyle = TextStyle(fontSize =  18.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor= Color(0xff16a085),
                    unfocusedBorderColor = Color(0xffcccccc)
                ),
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FieldPressureFramePreview() {
    FieldPressureFrame()
}
