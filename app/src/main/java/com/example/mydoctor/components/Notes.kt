package com.example.mydoctor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.R
import com.example.mydoctor.ui.theme.Black500
import com.example.mydoctor.ui.theme.White


@Composable
fun Notes(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(White)
    ){
        Row(modifier = Modifier){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.iconnotes),
                contentDescription = "icon notes",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Заметки",
                fontSize = 16.sp)
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.plus),
                contentDescription = "plus",
                modifier = Modifier
                    .size(18.dp)
                    
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            color = Black500,
            thickness = 1.dp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(text = "Опишите свое состояние",
            fontSize = 14.sp,
            color = Black500,
            modifier = Modifier
                .padding(vertical = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun NotesPreview() {
    Notes()
}

