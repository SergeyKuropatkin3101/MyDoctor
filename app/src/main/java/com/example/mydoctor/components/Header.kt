package com.example.mydoctor.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mydoctor.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("SimpleDateFormat")
@Composable
fun Header(modifier: Modifier = Modifier){
    Column(
        modifier = modifier
    ) {
        Row{
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
            )
            Text(
                text = stringResource(R.string.MyDoctorString)
            )
        }
        Row{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.Pressure)
                    )
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
                        textAlign = TextAlign.Center,
                        color = Color.Black,
//                fontWeight = FontWeight.Bold,
                    )
                }
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.plus),
                    contentDescription = "иконка настройки",
                    modifier = Modifier
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header()
}
