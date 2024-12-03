package com.example.mydoctor.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mydoctor.ViewModelProject.PressureViewModel
import com.example.mydoctor.presentation.homeScreen.HomeScreen
import com.example.mydoctor.presentation.secondScreen.SecondScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier, viewModel: PressureViewModel) {


    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route)
    {

        composable(Routes.HomeScreen.route) {
            HomeScreen(viewModel,navController, modifier)
        }

        composable(Routes.SecondScreen.route) {
            SecondScreen(viewModel,navController, modifier)
        }
    }
}
