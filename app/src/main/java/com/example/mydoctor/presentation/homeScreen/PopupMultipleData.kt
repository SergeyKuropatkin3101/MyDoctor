package com.example.mydoctor.presentation.homeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.Black300
import com.example.mydoctor.ui.theme.Black500
import com.example.mydoctor.ui.theme.Blue
import com.example.mydoctor.ui.theme.BorderColor
import com.example.mydoctor.ui.theme.White
import com.example.mydoctor.viewModelProject.MainScreenViewModel

@Composable
fun PopupMultipleData(vm: MainScreenViewModel){
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                border = BorderStroke(1.dp, BorderColor),
                shape = RoundedCornerShape(8.dp)
            )
            .background(White)
            .padding(16.dp)
    ) {
        var firstColumnWidth by remember { mutableStateOf(0) }
        Row {
            Column(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        firstColumnWidth = coordinates.size.width
                    }
            ){
                Text(
                    text = stringResource(R.string.upperPressure),
                    fontSize = 12.sp,
                    color = Black500,
                )
                Text(
                    text = vm.pressureUpperPopup.value,
                    fontSize = 18.sp,
                    fontWeight = W600,
                    color = Black1000,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = stringResource(R.string.millimetersMercury),
                    fontSize = 12.sp,
                    color = Black500,
                )

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column{
                Text(
                    text = stringResource(R.string.lowerPressure),
                    fontSize = 12.sp,
                    color = Black500,
                )
                Text(
                    text = vm.pressureLowerPopup.value,
                    fontSize = 18.sp,
                    fontWeight = W600,
                    color = Black1000,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = stringResource(R.string.millimetersMercury),
                    fontSize = 12.sp,
                    color = Black500,
                )

            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Column(
                modifier = Modifier
                    .width(with(LocalDensity.current) { firstColumnWidth.toDp() })
            ) {
                Text(
                    text = stringResource(R.string.textPulse),
                    fontSize = 12.sp,
                    color = Black500,
                )
                Text(
                    text = vm.pulsePopup.value,
                    fontWeight = W600,
                    fontSize = 18.sp,
                    color = Black1000,
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                )
                Text(
                    text = stringResource(R.string.beatsMinute),
                    fontSize = 12.sp,
                    color = Black500,
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column (
                modifier = Modifier
                    .align(Alignment.Bottom)
            ){
                Text(
                    text = vm.datePopup.value,
                    fontSize = 10.sp,
                    color = Black1000,
                )
                if(vm.isShowNote.value) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(4.dp)
                        ) {
                            drawCircle(
                                color = Blue,
                                center = center,
                                radius = 2.dp.toPx(),
                                style = Stroke(width = 0.5.dp.toPx())
                            )

                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.presenceNote),
                            fontSize = 10.sp,
                            color = Black300,
                        )
                    }
                }
            }

        }

    }
}

/*
@Preview(showBackground = true)
@Composable
fun PopupMultipleDataPreview() {
    PopupMultipleData()
}*/
