package com.example.mydoctor.component.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FirstFrame(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
    ) {
        TimeInterval()
        FrameWithGraph()
        Notes()
    }
}

@Preview(showBackground = true)
@Composable
fun FirstFramePreview() {
    FirstFrame()
}
