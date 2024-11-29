package com.example.mydoctor.component.secondScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun FrameWithContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HeaderSecondScreen(navController)
        FieldPressureFrame()
        FieldDateAndTimeFrame()
        AddDataNotes()
    }
}

@Preview(showBackground = true)
@Composable
fun FrameWithContentPreview() {
    val navController = rememberNavController()
    FrameWithContent(navController)
}
