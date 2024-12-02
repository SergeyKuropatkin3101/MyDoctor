package com.example.mydoctor.presentation.secondScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mydoctor.R
import com.example.mydoctor.ViewModelProject.PressureViewModel
import com.example.mydoctor.presentation.BrushDrawCircle
import com.example.mydoctor.ui.theme.Blue
import com.example.mydoctor.ui.theme.Blue300
import com.example.mydoctor.ui.theme.ColorEllipseBig
import com.example.mydoctor.ui.theme.ColorEllipseSmall
import com.example.mydoctor.ui.theme.White
import com.example.mydoctor.ui.theme.White_bg
import kotlinx.coroutines.launch


@Composable
fun SecondScreen(
    vm: PressureViewModel,
    navController: NavHostController,
    modifier: Modifier) {
    Box(
        modifier = modifier
            .background(White_bg)
            .fillMaxSize()
            .padding(top = 22.dp)
    ) {


        val radiusCircleSmall = 39
        val centerCircleSmallX = 4
        val centerCircleSmallY = 380
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
        val centerCircleBigY = 402
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HeaderSecondScreen(navController)
            FieldPressureFrame(vm)
            FieldDateAndTimeFrame(vm)
            AddDataNotes(vm)
        }

        val message = stringResource(id = R.string.textIncorrectData)

        val scope = rememberCoroutineScope()
        if (vm.snackbarHostVisible.value) {
            LaunchedEffect(key1 = Unit) {
                scope.launch {
                    vm.snackbarHostState.value.showSnackbar(
                        message = message,
                        withDismissAction = true
                    )

                }
            }
            vm.snackbarHostVisible.value = false
        }


        Button(
            onClick = { vm.insertData() },
            colors = ButtonDefaults.outlinedButtonColors(
                disabledContainerColor = Blue300,
                containerColor = Blue,
                contentColor = White,
            ),
            enabled = vm.saveButtonEnabled.value,
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
    val vm = hiltViewModel<PressureViewModel>()
    SecondScreen(vm,navController = navController, modifier = Modifier)
}
