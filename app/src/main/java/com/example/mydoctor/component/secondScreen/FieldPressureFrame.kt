package com.example.mydoctor.component.secondScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(text = stringResource(R.string.textBloodPressure))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = stringResource(R.string.upperPressure),
                        fontSize = 12.sp
                    )
                    val message = remember{mutableStateOf("Hello")}
                    OutlinedTextField(
                        message.value,
                        {message.value = it},
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        placeholder = { Text("Hello Work!") },
                        textStyle = TextStyle(fontSize =  18.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor= Color(0xff16a085), // цвет при получении фокуса
                            unfocusedBorderColor = Color(0xffcccccc)  // цвет при отсутствии фокуса
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.3f)
                    )
                }
                Column(
                modifier = Modifier
                ) {
                    Text(
                        text = stringResource(R.string.lowerPressure),
                        fontSize = 12.sp
                    )
                    val message = remember{mutableStateOf("Hello")}
                    OutlinedTextField(
                        message.value,
                        {message.value = it},
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true,
                        placeholder = { Text("Hello Work!") },
                        textStyle = TextStyle(fontSize =  18.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor= Color(0xff16a085),
                            unfocusedBorderColor = Color(0xffcccccc)
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.3f)

                    )
                }
            }
        }
        Column (
        ){
            Text(text = stringResource(R.string.textPulse))
            val message = remember{mutableStateOf("Hello")}
            OutlinedTextField(
                message.value,
                {message.value = it},
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                placeholder = { Text("Hello Work!") },
                textStyle = TextStyle(fontSize =  18.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor= Color(0xff16a085),
                    unfocusedBorderColor = Color(0xffcccccc)
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FieldPressureFramePreview() {
    FieldPressureFrame()
}
