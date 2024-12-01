package com.example.mydoctor.presentation.secondScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mydoctor.Navigation.Routes
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.White


@Composable
fun HeaderSecondScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                16.dp,
                4.dp,
                16.dp,
                0.dp
            )
    ) {
        IconButton(
            onClick = {navController.navigate(Routes.HomeScreen.route)},
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clip(RoundedCornerShape(9.dp))
                .size(32.dp)
                .background(White)
            ){
            Icon(
                painterResource(id = R.drawable.iconback),
                contentDescription = stringResource(R.string.buttonBack),
                tint = Black1000,
                modifier = Modifier
                    .size(
                        7.dp,
                        14.dp
                    )
            )
        }
        Text(text = stringResource(R.string.addData),
            fontSize = 18.sp,
            color = Black1000,
            modifier = Modifier
                .align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderSecondScreenPreview() {
    val navController = rememberNavController()
    HeaderSecondScreen(navController)
}
