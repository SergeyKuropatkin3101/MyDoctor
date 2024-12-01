package com.example.mydoctor.presentation.secondScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mydoctor.ViewModelProject.PressureViewModel


@Composable
fun FrameWithContent(
    vm: PressureViewModel,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        HeaderSecondScreen(navController)
        FieldPressureFrame(vm)
        FieldDateAndTimeFrame(vm)
        AddDataNotes(vm)
    }
}

@Preview(showBackground = true)
@Composable
fun FrameWithContentPreview() {
    val vm = hiltViewModel<PressureViewModel>()
    val navController = rememberNavController()
    FrameWithContent(vm,navController)
}
