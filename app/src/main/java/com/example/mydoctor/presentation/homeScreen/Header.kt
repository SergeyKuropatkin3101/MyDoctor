package com.example.mydoctor.presentation.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mydoctor.Navigation.Routes
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun Header(navController:NavHostController){
    Column {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.width(6.dp))
            val exo2font = FontFamily(
                Font(R.font.exo2_regular, FontWeight.W600)
            )
            Text(
                text = stringResource(R.string.MyDoctorString),
                fontFamily = exo2font,
                fontSize = 16.sp,
                color = Black1000,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = stringResource(R.string.Pressure),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Black1000
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    val dataFormat = SimpleDateFormat(
                        "MMMM yyyy 'г.'"
                    )

                    val currentDate = dataFormat.format(Date())

                    Text(
                        text = currentDate.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = Black1000
                    )
                }

                IconButton(
                    onClick = {navController.navigate(Routes.SecondScreen.route) },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clip(RoundedCornerShape(9.dp))
                        .size(32.dp)
                        .background(White)
                ) {
                    Icon(
                        painterResource(R.drawable.plus),
                        contentDescription = stringResource(R.string.textAddData),
                        tint = Black1000,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxSize()
                    )
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    val navController = rememberNavController()
    Header(navController)
}
