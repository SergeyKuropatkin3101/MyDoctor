package com.example.mydoctor.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FirstFrame(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
    ) {
        Row {
            Button(onClick = { }) {
                Text(text = "День")
            }

            Button(onClick = { }) {
                Text(text = "Неделя")
            }

            Button(onClick = { }) {
                Text(text = "Месяц")
            }
        }
        FrameWithGraph()
        Notes()
    }
}

@Preview(showBackground = true)
@Composable
fun FirstFramePreview() {
    FirstFrame()
}
