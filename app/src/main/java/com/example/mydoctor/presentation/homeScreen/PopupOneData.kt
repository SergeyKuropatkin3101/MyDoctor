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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
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
fun PopupOneData(vm: MainScreenViewModel) {
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                border = BorderStroke(1.dp, BorderColor),
                shape = RoundedCornerShape(8.dp)
            )
            .background(White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Column{
                Text(
                    text = stringResource(R.string.Pressure),
                    fontSize = 12.sp,
                    color = Black500,
                )
                Text(
                    text = vm.pressurePopup.value,
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
        }

        Spacer(modifier = Modifier.height(8.dp))
        Column {
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

/*
@Preview(showBackground = true)
@Composable
fun PopupOneDataPreview() {
    PopupOneData()
}*/
