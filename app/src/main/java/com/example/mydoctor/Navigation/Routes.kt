package com.example.mydoctor.Navigation

sealed class Routes(val route: String) {
    object HomeScreen: Routes("home")
    object SecondScreen: Routes("addPressure")
}