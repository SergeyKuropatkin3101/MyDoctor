package com.example.mydoctor.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mydoctor.component.homeScreen.HomeScreen
import com.example.mydoctor.component.secondScreen.SecondScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route)
    {

        composable(Routes.HomeScreen.route) {
            HomeScreen(navController, modifier)
        }

        composable(Routes.SecondScreen.route) {
            SecondScreen(navController, modifier)
        }
    }
}