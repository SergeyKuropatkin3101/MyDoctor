package com.example.mydoctor.presentation.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black1000
import com.example.mydoctor.ui.theme.Black300
import com.example.mydoctor.ui.theme.Black500
import com.example.mydoctor.ui.theme.White


@Composable
fun Notes() {
    Column(
        modifier = Modifier
            .padding(
                16.dp,
                0.dp
            )
            .clip(RoundedCornerShape(24.dp))
            .background(White)
            .padding(16.dp)
    ){
        Row(modifier = Modifier){
            Image(
                painterResource(id = R.drawable.iconnotes),
                contentDescription = stringResource(R.string.textDescriptionIconNote),
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.textNotes),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(
                            16.dp,
                            6.dp
                        )

                )
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(3.dp, 6.dp)
                        .align(Alignment.CenterEnd)
                        .size(18.dp)
                ) {
                    Icon(
                        painterResource(id = R.drawable.plus),
                        contentDescription = stringResource(R.string.addNotes),
                        tint = Black1000,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    
                }
            }
        }
        HorizontalDivider(
            color = Black500,
            thickness = 1.dp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(text = stringResource(R.string.descriptionCondition),
            fontSize = 14.sp,
            color = Black300,
            modifier = Modifier
                .padding(
                    16.dp,
                    8.dp,
                    16.dp,
                    16.dp
                )
        )
    }
}



@Preview(showBackground = true)
@Composable
fun NotesPreview() {
    Notes()
}

