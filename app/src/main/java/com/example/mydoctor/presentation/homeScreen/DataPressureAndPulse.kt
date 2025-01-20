package com.example.mydoctor.presentation.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.Black500
import com.example.mydoctor.viewModelProject.MainScreenViewModel

@Composable
fun DataPressureAndPulse(vm: MainScreenViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ){

        var secondColumnHeight by remember { mutableStateOf(0) }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(with(LocalDensity.current) { secondColumnHeight.toDp() })
        ) {
            Text(
                text = stringResource(R.string.Pressure),
                fontSize = 12.sp,
                color = Black500,
            )
            Text(
                text = stringResource(R.string.textPulse),
                fontSize = 12.sp,
                color = Black500,
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    secondColumnHeight = coordinates.size.height
                }
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = vm.pressureOnMainScreen.value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    color = Black1000
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.millimetersMercury),
                    fontSize = 12.sp,
                    color = Black500,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(

                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = vm.pulseOnMainScreen.value,
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    color = Black1000
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.beatsMinute),
                    fontSize = 12.sp,
                    color = Black500,
                )
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DataPressureAndPulsePreview() {
    DataPressureAndPulse()
}*/