package com.example.mydoctor.component.homeScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Graph() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {

    }
}


/*    Box (modifier = Modifier.fillMaxSize()){
        Text("kfhgkds" +
                "]dfgd" +
                "drgd" +
                "rg" +
                "rg" +
                "rghtsrtthdthdthdhdhdthdthdthdthdthdhdhdthdthhfg")
        val popupWidth = 200.dp
        val popupHeight = 50.dp
        val cornerSize = 16.dp
        Popup(alignment = Alignment.Center)
        {         // Draw a rectangle shape with rounded corners inside the popup
              Box(
                  Modifier
                      .size(popupWidth, popupHeight)
                      .background(White, RoundedCornerShape(cornerSize))
              ){
                  Text("slajkif;aksh")
              }
        }
    }

}
*/
@Preview(showBackground = true)
@Composable
fun GraphPreview() {
    Graph()
}