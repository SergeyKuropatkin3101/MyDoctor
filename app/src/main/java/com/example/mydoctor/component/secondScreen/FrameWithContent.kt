package com.example.mydoctor.component.secondScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FrameWithContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HeaderSecondScreen()
        FieldPressureFrame()
        FieldDateAndTimeFrame()
        addDataNotes()
    }
}

@Preview(showBackground = true)
@Composable
fun FrameWithContentPreview() {
    FrameWithContent()
}
