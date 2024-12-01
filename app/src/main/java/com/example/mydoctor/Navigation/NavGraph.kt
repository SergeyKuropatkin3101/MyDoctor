package com.example.mydoctor.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mydoctor.ViewModelProject.PressureViewModel
import com.example.mydoctor.presentation.homeScreen.HomeScreen
import com.example.mydoctor.presentation.secondScreen.SecondScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {

    val viewModel = hiltViewModel<PressureViewModel>()
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

/*
val navController = rememberNavController()
 NavHost(navController, startDestination = "ExampleRoute") {
    composable("ExampleRoute") {
        val viewModel = hiltViewModel&lt;ExampleViewModel&gt;()
    }
}

val navController = rememberNavController()
  NavHost(navController, startDestination = "Parent") {
    navigation(startDestination = "InnerRouteA", route = "Parent") {
        composable("InnerRouteA") {
            val parentEntry = remember { navController.getBackStackEntry("Parent") }
            val viewModel = hiltViewModel&lt;ParentViewModel&gt;(parentEntry)
        }
        composable("InnerRouteB") {
            val parentEntry = remember { navController.getBackStackEntry("Parent") }
            val viewModel = hiltViewModel&lt;ParentViewModel&gt;(parentEntry)
        }
    }
}*/
