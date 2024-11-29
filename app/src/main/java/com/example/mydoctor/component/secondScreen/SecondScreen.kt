package com.example.mydoctor.component.secondScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Blue300
import com.example.mydoctor.ui.theme.ColorEllipseBig
import com.example.mydoctor.ui.theme.ColorEllipseSmall
import com.example.mydoctor.ui.theme.White
import com.example.mydoctor.ui.theme.White_bg


@Composable
fun SecondScreen(navController: NavHostController, modifier: Modifier) {
    Box(
        modifier = modifier
            .background(White_bg)
            .fillMaxSize()
            .padding(top = 22.dp)
    ) {
        Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        translate(left = -4.dp.toPx(), top = 380.dp.toPx()) {
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
            translate(left = -27.dp.toPx(), top = 402.dp.toPx()) {
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
        FrameWithContent(navController)
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Blue300,
                contentColor = White,
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(
                    24.dp,
                    16.dp
                )
        ) {
            Text(
                text = stringResource(R.string.textSave),
                fontSize = 18.sp,
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    val navController = rememberNavController()
    SecondScreen(navController = navController, modifier = Modifier)
}
