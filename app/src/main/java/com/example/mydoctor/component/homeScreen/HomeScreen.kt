package com.example.mydoctor.component.homeScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mydoctor.ui.theme.ColorEllipseBig
import com.example.mydoctor.ui.theme.ColorEllipseSmall

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            translate(left = -4.dp.toPx(), top = 98.dp.toPx()) {
                val radiusCircleSmall = 39.dp.toPx()
                val centerCircle = Offset(x = size.width, y = 0.0f)
                val colorStops = arrayOf(
                    0.5f to ColorEllipseSmall,
                    1f to Color.Transparent)
                val brush = Brush.radialGradient(
                    colorStops = colorStops,
                    center = centerCircle,
                    radius = radiusCircleSmall,
                    tileMode = TileMode.Decal
                )
                drawCircle(
                    brush,
                    radius = radiusCircleSmall,
                    center = centerCircle
                )
            }
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ){
            translate(left = -27.dp.toPx(), top = 120.dp.toPx()) {
                val radiusCircleBig = 69.dp.toPx()
                val centerCircle = Offset(x = size.width, y = 0.0f)
                val colorStops = arrayOf(
                    0.7f to ColorEllipseBig,
                    1f to Color.Transparent)
                val brush = Brush.radialGradient(
                    colorStops = colorStops,
                    center = centerCircle,
                    radius = radiusCircleBig,
                    tileMode = TileMode.Decal,
                )
                drawCircle(
                    brush,
                    radius = 69.dp.toPx(),
                    center = Offset(x = size.width, y = 0.0f)
                )
            }

        }
        Column(
        ) {
            Header()
            FirstFrame()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
