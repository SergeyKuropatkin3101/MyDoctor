package com.example.mydoctor.presentation.homeScreen

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mydoctor.Navigation.Routes
import com.example.mydoctor.R
import com.example.mydoctor.ViewModelProject.PressureViewModel
import com.example.mydoctor.ui.theme.AddDataColor
import com.example.mydoctor.ui.theme.Black300
import com.example.mydoctor.ui.theme.Orange80
import com.example.mydoctor.ui.theme.White
import com.example.mydoctor.ui.theme.Yellow80
import kotlinx.coroutines.launch

@Composable
fun FrameWithGraph(vm: PressureViewModel, navController: NavHostController) {
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

        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch() {
                vm.delayTooltip()
            }
        }
        OutlinedButton (
            onClick = {
                navController
                    .navigate(
                        Routes.SecondScreen.route
                    ){
                        launchSingleTop = true
                    }
            },

            border = BorderStroke( width = Dp.Hairline, AddDataColor),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = AddDataColor,
            ),
            contentPadding = PaddingValues(0.dp,0.dp),
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.End)
                .padding(
                    16.dp,
                    16.dp,
                    16.dp,
                    24.dp
                )
                .tooltip(
                    title = stringResource(R.string.tooltipTitle),
                    text = stringResource(R.string.tooltipText),
                    enabled = vm.enabledTooltip.value,
                    onDismiss = { vm.enabledTooltip.value = false }
                )



        ) {

            Text(text = stringResource(R.string.addData),
                color = AddDataColor,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                modifier = Modifier
                    .padding(16.dp,8.dp)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun FrameWithGraphPreview() {
    val vm = hiltViewModel<PressureViewModel>()
    val navController = rememberNavController()
    FrameWithGraph(vm, navController)
}