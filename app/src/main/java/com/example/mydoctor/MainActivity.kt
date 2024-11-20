package com.example.mydoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mydoctor.components.FirstFrame
import com.example.mydoctor.components.Header
import com.example.mydoctor.ui.theme.MyDoctorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyDoctorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Header()
                    FirstFrame()
                }


//                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
//                    GreetingText(
//                        message = "Hello my friend",
//                        from = "Hid",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MyDoctorPreview() {
    MyDoctorTheme {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Header()
            FirstFrame()
        }
    }
}