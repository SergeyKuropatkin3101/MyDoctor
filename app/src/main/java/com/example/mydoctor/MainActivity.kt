package com.example.mydoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mydoctor.component.secondScreen.SecondScreen
import com.example.mydoctor.ui.theme.MyDoctorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyDoctorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    SecondScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}