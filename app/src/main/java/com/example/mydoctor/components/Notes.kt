package com.example.mydoctor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mydoctor.R


@Composable
fun Notes(modifier: Modifier = Modifier) {
    Column(modifier = Modifier){
        Row(modifier = Modifier){
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.iconnotes),
                contentDescription = "icon notes",
                modifier = Modifier.size(24.dp)
            )
            Text(text = "Заметки")
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.plus),
                contentDescription = "plus",
                modifier = Modifier.size(18.dp)
            )
        }
        Text(text = "Опишите свое состояние")
    }
}



@Preview(showBackground = true)
@Composable
fun NotesPreview() {
    Notes()
}

