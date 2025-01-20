package com.example.mydoctor.presentation.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black
import com.example.mydoctor.ui.theme.White
import com.example.mydoctor.viewModelProject.MainScreenViewModel


@Composable
fun TimeInterval(vm: MainScreenViewModel) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                16.dp,
                8.dp
            )
            .clip(
                RoundedCornerShape(24.dp)
            )
            .background(White)
            .padding(
                16.dp,
                8.dp
            )

    ){

        TextButton(
                onClick = {vm.showDayDataPressure() },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(vm.colorDayButtonTime.value)
                    .height(26.dp)

            ) {
                Text(text = stringResource(R.string.textDay),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Black,
                    )
            }

        TextButton(
                onClick = {vm.showWeekDataPressure() },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(vm.colorWeekButtonTime.value)
                    .height(26.dp)
            ) {
                Text(text = stringResource(R.string.textWeek),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Black)
            }

        TextButton(
                onClick = {vm.showMonthDataPressure() },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(vm.colorMonthButtonTime.value)
                    .height(26.dp)
            ) {
                Text(text = stringResource(R.string.textMonth),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Black)
            }
    }
}

/*

@Preview(showBackground = true)
@Composable
fun TimeIntervalPreview() {
    TimeInterval()
}
*/
