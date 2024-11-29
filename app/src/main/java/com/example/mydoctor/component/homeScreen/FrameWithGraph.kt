package com.example.mydoctor.component.homeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mydoctor.Navigation.Routes
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.AddDataColor
import com.example.mydoctor.ui.theme.Black300
import com.example.mydoctor.ui.theme.Orange80
import com.example.mydoctor.ui.theme.White
import com.example.mydoctor.ui.theme.Yellow80

@Composable
fun FrameWithGraph(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(
                17.dp,
                16.dp
            )
            .clip(RoundedCornerShape(24.dp))
            .background(White)
    ) {
        Column(
            modifier = Modifier
            .padding(
                16.dp,
                24.dp,
                16.dp,
                16.dp
            )
        ) {
            Text(text = stringResource(R.string.textNoData),
                fontSize = 18.sp)
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            Text(text = stringResource(R.string.TextToday),
                fontSize = 10.sp
            )
        }
        HorizontalDivider(
            color = Black300,
            thickness = 1.dp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(
                    16.dp,
                    0.dp
                )
        )
        Row(
            modifier = Modifier
                .padding(16.dp),
            Arrangement.spacedBy(16.dp)
        ){
            Canvas(
                modifier = Modifier
                    .size(16.dp)
            ) {
                drawCircle(
                    color = Orange80,
                    center = center,
                    radius = 8.dp.toPx()
                )

            }
            Text(
                text = stringResource(R.string.upperPressure),
                fontSize = 12.sp,
                modifier = Modifier
                    .weight(400.0f)
            )
            Canvas(
                modifier = Modifier
                    .size(16.dp)
            ) {
                drawCircle(
                    color = Yellow80,
                    center = center,
                    radius = 8.dp.toPx()
                )

            }
            Text(
                text = stringResource(R.string.lowerPressure),
                fontSize = 12.sp,
                modifier = Modifier
                    .weight(400.0f)
            )

        }

        Graph()
        var enabledTooltip by remember {
            mutableStateOf(true)
        }
        TextButton (
            onClick = {
                navController
                    .navigate(
                        Routes.SecondScreen.route
                    ){
                        launchSingleTop = true
                    }
            },
            modifier = Modifier

                .align(Alignment.End)
                .padding(
                    16.dp,
                    16.dp,
                    16.dp,
                    24.dp
                )
                .tooltip(
                    title = "Добавьте данные",
                    text = "Добавить данные можно, кликнув на кнопку. Или попробуйте отсканировать данные на вашем аппарате.",
                    enabled = enabledTooltip,
                    onDismiss = {enabledTooltip = false}
                ),
            border = BorderStroke( width = Dp.Hairline, AddDataColor),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = AddDataColor,
            ),
            contentPadding = PaddingValues(16.dp,8.dp),



        ) {
            /*PlainTooltipBox(
            tooltip = { Text(text = "Yes, I am a tooltip") }
        ) {
            Button(
                onClick = {},
                modifier = Modifier.tooltipTrigger()
            ) {
                Text(text = "Android")
            }
        }*/
            Text(text = stringResource(R.string.addData),
                color = AddDataColor,
                fontSize = 12.sp,
                lineHeight = 12.sp
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun FrameWithGraphPreview() {
    val navController = rememberNavController()
    FrameWithGraph(navController)
}