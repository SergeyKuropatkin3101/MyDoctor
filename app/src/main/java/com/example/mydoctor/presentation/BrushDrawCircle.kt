package com.example.mydoctor.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp

@Composable
fun BrushDrawCircle(
    radiusCircle: Int,
    colorStops: Array<Pair<Float, Color>>,
    centerCircleX: Int,
    centerCircleY: Int
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val radiusCircle = radiusCircle.dp.toPx()
        val centerCircle = Offset(x = size.width - centerCircleX.dp.toPx(), y = centerCircleY.dp.toPx())
        val brush = Brush.radialGradient(
            colorStops = colorStops,
            center = centerCircle,
            radius = radiusCircle,
            tileMode = TileMode.Decal
        )
        drawCircle(
            brush,
            radius = radiusCircle,
            center = centerCircle
        )
    }
}