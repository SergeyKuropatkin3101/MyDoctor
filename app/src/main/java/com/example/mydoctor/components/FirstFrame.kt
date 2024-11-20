package com.example.mydoctor.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FirstFrame(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
    ) {
        TimeInterval()
        FrameWithGraph()
        Spacer(modifier = Modifier.height(8.dp))
        Notes()
    }
}

@Preview(showBackground = true)
@Composable
fun FirstFramePreview() {
    FirstFrame()
}
