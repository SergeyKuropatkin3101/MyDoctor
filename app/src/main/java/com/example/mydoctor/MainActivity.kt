package com.example.mydoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mydoctor.Navigation.NavGraph
import com.example.mydoctor.ui.theme.MyDoctorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        WindowInsetsControllerCompat()
//        val db = InventoryDatabase.getDatabase(this)
//        val dataPressureDao = db.dataPressureDao()
//        val viewModel = PressureViewModel(dataPressureDao)

        setContent {
            MyDoctorTheme {
                Scaffold{ innerPadding ->
                    val navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding))
                }

            }
        }
    }
}