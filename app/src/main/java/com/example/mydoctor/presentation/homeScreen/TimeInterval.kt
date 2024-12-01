package com.example.mydoctor.presentation.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black
import com.example.mydoctor.ui.theme.White


@Composable
fun TimeInterval() {
    Row (
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                16.dp,
                8.dp,
                16.dp,
                8.dp
            )
            .clip(
                RoundedCornerShape(24.dp)
            )
            .background(White)

    ){
        TextButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.textDay),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Black
                )
            }

        TextButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.textWeek),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Black)
            }

        TextButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.textMonth),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Black)
            }
    }
}


@Preview(showBackground = true)
@Composable
fun TimeIntervalPreview() {
    TimeInterval()
}
