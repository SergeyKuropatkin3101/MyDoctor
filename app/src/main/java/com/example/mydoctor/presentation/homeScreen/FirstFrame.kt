package com.example.mydoctor.presentation.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun FirstFrame(navController: NavHostController) {
    Column {
        TimeInterval()
        FrameWithGraph(navController)
        Notes()
    }
}

@Preview(showBackground = true)
@Composable
fun FirstFramePreview() {
    val navController = rememberNavController()
    FirstFrame(navController)
}
