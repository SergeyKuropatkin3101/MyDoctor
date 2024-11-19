package com.example.mydoctor.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mydoctor.ui.theme.AddDataColor
import com.example.mydoctor.ui.theme.Black300
import com.example.mydoctor.ui.theme.Orange80
import com.example.mydoctor.ui.theme.Yellow80

@Composable
fun FrameWithGraph(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Column(modifier = modifier) {
            Text(text = "Нет данных")
            Text(text = "Сегодня")
        }
        HorizontalDivider(
            color = Black300,
            thickness = 1.dp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
        Row(modifier = Modifier){
            Canvas(Modifier) {
                drawCircle(
                    color = Orange80,
                    center = center,
                    radius = 8.dp.toPx()
                )
            }
            Text(text = "Систолическое", modifier = Modifier.weight(400.0f))
            Canvas(Modifier) {
                drawCircle(
                    color = Yellow80,
                    center = center,
                    radius = 8.dp.toPx()
                )
            }
            Text(text = "Диастолическое", modifier = Modifier.weight(400.0f))

        }

        Graph()

        Button(
            onClick = { },
            modifier = Modifier
            .padding(
                16.dp,
                8.dp,
                16.dp,
                8.dp
            )
            ,border = BorderStroke( width = Dp.Hairline, AddDataColor),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White,
                contentColor = AddDataColor)
        ) {
            Text(text = "Добавить данные",
                color = AddDataColor)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun FrameWithGraphPreview() {
    FrameWithGraph()
}