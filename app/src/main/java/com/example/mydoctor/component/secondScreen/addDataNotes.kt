package com.example.mydoctor.component.secondScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.Black300
import com.example.mydoctor.ui.theme.White


@Composable
fun addDataNotes(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                16.dp,
                0.dp
            )

    ){
        Row(modifier = Modifier) {
            Text(
                text = stringResource(R.string.textNote),
                fontSize = 16.sp,
                color = Black1000,
                modifier = Modifier
                    .padding(
                        16.dp,
                        6.dp
                    )

            )


        }

        Text(text = stringResource(R.string.descriptionCondition),
            fontSize = 18.sp,
            color = Black300,
            modifier = Modifier
                .padding(
                    16.dp,
                    8.dp,
                    16.dp,
                    16.dp
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(White)
                .padding(16.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun addDataNotesPreview() {
    addDataNotes()
}

