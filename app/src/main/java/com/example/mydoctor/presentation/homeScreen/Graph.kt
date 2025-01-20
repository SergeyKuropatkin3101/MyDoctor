package com.example.mydoctor.presentation.homeScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.mydoctor.ui.theme.Black
import com.example.mydoctor.ui.theme.Black500
import com.example.mydoctor.ui.theme.LabelBottomInGraph
import com.example.mydoctor.ui.theme.Line150Color
import com.example.mydoctor.ui.theme.Line200100Color
import com.example.mydoctor.ui.theme.Line50Color
import com.example.mydoctor.ui.theme.LineLowerColor
import com.example.mydoctor.ui.theme.LineUpperColor
import com.example.mydoctor.ui.theme.PointLowerColor
import com.example.mydoctor.ui.theme.PointUpperColor
import com.example.mydoctor.viewModelProject.MainScreenViewModel
import com.example.mydoctor.viewModelProject.WindowUpGraphOffsetPositionProvider
import kotlin.math.max
import kotlin.math.min


@Composable
fun Graph(vm: MainScreenViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ){

        if (vm.isPopupShow.value) {
            if(vm.isCheckOnePopup()){
                Popup(
                    popupPositionProvider = WindowUpGraphOffsetPositionProvider(),
                    onDismissRequest = {
                        vm.isShowNote.value = false
                        vm.isPopupShow.value = false
                    }
                ) {
                    PopupOneData(vm)
                }
            }
            else {
                Popup(
                    popupPositionProvider = WindowUpGraphOffsetPositionProvider(),
                    onDismissRequest = {
                        vm.isShowNote.value = false
                        vm.isPopupShow.value = false
                    }
                ) {
                    PopupMultipleData(vm)
                }
            }
        }
        val textMeasurer = rememberTextMeasurer()
        Canvas(
        modifier = Modifier
            .weight(1f)
            .height(150.dp)
            .scrollable(
                orientation = Orientation.Horizontal,
                state = rememberScrollableState { delta ->
                    vm.changeOffsetX()
                    if (vm.isScrollBackwardEnabled) {
                        if (delta > 0) {
                            vm.offset.floatValue += min(
                                delta,
                                vm.maxAvailableOffsetForBackward.floatValue
                            )
                        }
                    }
                    if (vm.isScrollForwardEnabled) {
                        if (delta < 0) {
                            vm.offset.floatValue += max(
                                delta,
                                vm.maxAvailableOffsetForForward.floatValue
                            )
                        }
                    }
                    delta
                }
            )
            .clipToBounds()
            .pointerInput(Unit) {
                detectTapGestures { offsetPress ->
                    vm.offsetSelectedPoint.value = offsetPress
                    vm.isCheckOffsetPressAndPoint()
                    offsetPress
                }
            }
        ) {

            val paddingVerticalInGraph = 16.dp.toPx()
            vm.heightGraph.floatValue = size.height - paddingVerticalInGraph
            vm.widthGraph.floatValue = size.width
            vm.onePxFromDp.floatValue = 1.dp.toPx()
            drawLine(
                start = Offset(x = 0f, y = vm.heightGraph.floatValue - 0.5.dp.toPx()),
                end = Offset(x = vm.widthGraph.floatValue, y = vm.heightGraph.floatValue - 0.5.dp.toPx()),
                color = Black,
                strokeWidth = 1.dp.toPx()
            )
            drawLine(
                start = Offset(x = vm.widthGraph.floatValue - 0.5.dp.toPx(), y = 0f),
                end = Offset(x = vm.widthGraph.floatValue - 0.5.dp.toPx(), y = vm.heightGraph.floatValue),
                color = Black,
                strokeWidth = 1.dp.toPx()
            )
            drawLine(
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = vm.widthGraph.floatValue, y = 0f),
                color = Line200100Color,
                strokeWidth = 0.5.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
            )
            drawLine(
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = 0f, y = vm.heightGraph.floatValue),
                color = Line200100Color,
                strokeWidth = 0.5.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
            )
            drawLine(
                start = Offset(x = 0f, y = vm.heightGraph.floatValue / 4),
                end = Offset(x = vm.widthGraph.floatValue, y = vm.heightGraph.floatValue / 4),
                color = Line150Color,
                strokeWidth = 1.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
            )
            drawLine(
                start = Offset(x = 0f, y = 2 * vm.heightGraph.floatValue / 4),
                end = Offset(x = vm.widthGraph.floatValue, y = 2 * vm.heightGraph.floatValue / 4),
                color = Line200100Color,
                strokeWidth = 0.5.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
            )
            drawLine(
                start = Offset(x = 0f, y = 3 * vm.heightGraph.floatValue / 4),
                end = Offset(x = vm.widthGraph.floatValue, y = 3 * vm.heightGraph.floatValue / 4),
                color = Line50Color,
                strokeWidth = 1.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
            )

            for (i in vm.listLabelForGraph.indices) {
//                Log.i("vm.listLabelForGraph[i].label", vm.listLabelForGraph[i].label)
                val textLayoutResult = textMeasurer.measure(
                    text = vm.listLabelForGraph[i].label,
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = LabelBottomInGraph
                    )
                )
                drawText(
                    textLayoutResult,
                    topLeft = Offset(
                        vm.listLabelForGraph[i].x+vm.offset.floatValue,
                        vm.listLabelForGraph[i].y
                    )
                )
            }

            drawPoints(
                points = vm.listPointForGraph.map { Offset(it.x+vm.offset.floatValue,it.yUpper) },
                strokeWidth = 4.dp.toPx(),
                pointMode = PointMode.Points,
                color = PointUpperColor,
                cap = StrokeCap.Round
            )
            drawPoints(
                points = vm.listPointForGraph.map { Offset(it.x+vm.offset.floatValue,it.yLower) },
                strokeWidth = 4.dp.toPx(),
                pointMode = PointMode.Points,
                color = PointLowerColor,
                cap = StrokeCap.Round
            )
            if (vm.isChecklistMore2()) {
                val (cubicPointsUpper, cubicPointsLower) = vm.getCubicPoints()
                val pathUpper = Path()
                val pathLower = Path()
                pathUpper.moveTo(
                    vm.listPointForGraph.first().x,
                    vm.listPointForGraph.first().yUpper
                )
                pathLower.moveTo(
                    vm.listPointForGraph.first().x,
                    vm.listPointForGraph.first().yLower
                )
                for (i in 1 until vm.listPointForGraph.size) {
                    pathUpper.cubicTo(
                        cubicPointsUpper.first[i - 1].x,
                        cubicPointsUpper.first[i - 1].y,
                        cubicPointsUpper.second[i - 1].x,
                        cubicPointsUpper.second[i - 1].y,
                        vm.listPointForGraph[i].x,
                        vm.listPointForGraph[i].yUpper
                    )
                    pathLower.cubicTo(
                        cubicPointsLower.first[i - 1].x,
                        cubicPointsLower.first[i - 1].y,
                        cubicPointsLower.second[i - 1].x,
                        cubicPointsLower.second[i - 1].y,
                        vm.listPointForGraph[i].x,
                        vm.listPointForGraph[i].yLower
                    )
                }

                translate(vm.offset.floatValue,0f){
                    drawPath(
                    pathUpper,
                    LineUpperColor,
                    style = Stroke(width = 5f)
                )
                drawPath(
                    pathLower,
                    LineLowerColor,
                    style = Stroke(width = 5f)
                )
                }
            }
        }
        Canvas(modifier = Modifier
            .size(20.dp, 150.dp)
        ){
            val spaceBetweenLabelEnd = (size.height - 24.dp.toPx()) / 4
            val endLabel = listOf("200", "150", "100", "50", "0")
            var textStyle = TextStyle(
                fontSize = 10.sp
            )
            for (i in endLabel.indices) {

                when (i) {
                    1 -> {
                        textStyle = textStyle.plus(
                            TextStyle(
                                color = Line150Color.copy(alpha = 1.0f)
                            )
                        )
                    }

                    3 -> {
                        textStyle = textStyle.plus(
                            TextStyle(
                                color = Line50Color.copy(alpha = 1.0f)
                            )
                        )
                    }

                    else -> {
                        textStyle = textStyle.plus(
                            TextStyle(
                                color = Black500
                            )
                        )
                    }
                }
                val textLayoutResult = textMeasurer.measure(
                    text = endLabel[i],
                    style = textStyle
                )
                var xPlusOffset = 3.dp.toPx()
                if (i == 3) {
                    xPlusOffset += 2.dp.toPx()
                } else if (i == 4) {
                    xPlusOffset += 4.dp.toPx()
                }
                drawText(
                    textLayoutResult,
                    topLeft = Offset(0 + xPlusOffset, i * spaceBetweenLabelEnd - 2.dp.toPx())
                )
            }
        }




    }
}



/*@Preview(showBackground = true)
@Composable
fun GraphPreview() {
    val dataPressureDay = listOf(
        DataPressure(0,"180","106","70", Date(124,11,11,0,2),"Note"),
        DataPressure(1,"110","80","50", Date(124,11,11,5,51),"Note1"),
        DataPressure(2,"120","85","65", Date(124,11,11,10,35),"Note2"),
        DataPressure(3,"114","70","87", Date(124,11,11,15,0),"Note3"),
        DataPressure(4,"150","115","74", Date(124,11,11,23,59),"Note4")
    )
    val dataPressureWeek = listOf(
        DataPressure(0,"180","106","70", Date(124,11,5,0,5),"Note"),
        DataPressure(1,"110","80","50", Date(124,11,7,0,51),"Note1"),
        DataPressure(2,"120","85","65", Date(124,11,8,20,35),"Note2"),
        DataPressure(3,"114","70","87", Date(124,11,9,20,0),"Note3"),
        DataPressure(4,"150","115","74", Date(124,11,11,22,59),"Note4")
    )
    Graph(dataPressureWeek)
}*/

