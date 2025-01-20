package com.example.mydoctor.presentation.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mydoctor.presentation.BrushDrawCircle
import com.example.mydoctor.ui.theme.ColorEllipseBig
import com.example.mydoctor.ui.theme.ColorEllipseSmall
import com.example.mydoctor.ui.theme.White_bg
import com.example.mydoctor.viewModelProject.MainScreenViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val vm = hiltViewModel<MainScreenViewModel>()
    Box (
        modifier = modifier
            .background(White_bg)
    ){
        val radiusCircleSmall = 39
        val centerCircleSmallX = 4
        val centerCircleSmallY = 98
        val colorStopsSmall = arrayOf(
            0.5f to ColorEllipseSmall,
            1f to Color.Transparent
        )
        BrushDrawCircle(
            radiusCircle = radiusCircleSmall,
            colorStops = colorStopsSmall,
            centerCircleX = centerCircleSmallX,
            centerCircleY = centerCircleSmallY,
        )

        val radiusCircleBig = 69
        val centerCircleBigX = 27
        val centerCircleBigY = 120
        val colorStopsBig = arrayOf(
            0.7f to ColorEllipseBig,
            1f to Color.Transparent
        )
        BrushDrawCircle(
            radiusCircle = radiusCircleBig,
            colorStops = colorStopsBig,
            centerCircleX = centerCircleBigX,
            centerCircleY = centerCircleBigY,
        )
        Column {
            Header(navController)
            Column {
//                TimeInterval(vm)
                TimeInterval(vm)
                FrameWithGraph(vm,navController)
                Notes(vm)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController, modifier = Modifier)
}
