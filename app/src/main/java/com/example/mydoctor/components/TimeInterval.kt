package com.example.mydoctor.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.ui.theme.AddDataColor
import com.example.mydoctor.ui.theme.Black


@Composable
fun TimeInterval(modifier: Modifier = Modifier) {
    Row (
        modifier = Modifier
            .padding(
                16.dp,
                8.dp,
                16.dp,
                8.dp
            )
            .clip(
                RoundedCornerShape(24.dp)
            )

    ){
            Button(
                onClick = { },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = AddDataColor)
            ) {
                Text(text = "День",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Black
                )
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = AddDataColor)
            ) {
                Text(text = "Неделя",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Black)
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = AddDataColor)
            ) {
                Text(text = "Месяц",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Black)
            }
    }
}


@Preview(showBackground = true)
@Composable
fun TimeIntervalPreview() {
    TimeInterval()
}
