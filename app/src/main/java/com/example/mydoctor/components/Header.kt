package com.example.mydoctor.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("SimpleDateFormat")
@Composable
fun Header(modifier: Modifier = Modifier){
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
            )
            Spacer(modifier = modifier.width(6.dp))
            val exo2font = FontFamily(
                Font(R.font.exo2_regular, FontWeight.W600)
            )
            Text(
                text = stringResource(R.string.MyDoctorString),
                fontFamily = exo2font,
                fontSize = 16.sp,
                color = Black1000
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.Pressure),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = Black1000
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    val dataFormat = SimpleDateFormat(
                        "MMMM yyyy 'г.'"
                    )

                    // Создание переменной для текущей даты
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
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .shadow(4.dp)
                        .clip(
                            RoundedCornerShape(
                                 10.dp
                            )
                        )
                ){

                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.plus),
                            contentDescription = "иконка настройки",
                            modifier = Modifier
                                .background(White)
                                .padding(4.dp)
                        )

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header()
}
