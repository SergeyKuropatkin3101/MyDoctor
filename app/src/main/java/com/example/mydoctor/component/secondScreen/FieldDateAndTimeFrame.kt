package com.example.mydoctor.component.secondScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun FieldDateAndTimeFrame(modifier: Modifier = Modifier) {
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
            TextButton(
                onClick = { },
                modifier = Modifier
            ) {
                val dataFormat = SimpleDateFormat(
                    "dd.MM.yyyy"
                )

                val currentDate = dataFormat.format(Date())
                Text(text = currentDate,
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
                onClick = { },
                modifier = Modifier
            ) {

                val timeFormat = SimpleDateFormat(
                    "HH:mm"
                )

                val currentTime = timeFormat.format(Date())
                Text(text = currentTime,
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
    FieldDateAndTimeFrame()
}
