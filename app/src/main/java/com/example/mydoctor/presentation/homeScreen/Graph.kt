package com.example.mydoctor.presentation.homeScreen

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mydoctor.ViewModelProject.StatusTimeIntervalButtons
import com.example.mydoctor.data.DataPressure
import com.example.mydoctor.ui.theme.Black
import com.example.mydoctor.ui.theme.Black500
import com.example.mydoctor.ui.theme.LabelButtonInGraph
import com.example.mydoctor.ui.theme.Line150Color
import com.example.mydoctor.ui.theme.Line200100Color
import com.example.mydoctor.ui.theme.Line50Color
import com.example.mydoctor.ui.theme.LineLowerColor
import com.example.mydoctor.ui.theme.LineUpperColor
import com.example.mydoctor.ui.theme.PointLowerColor
import com.example.mydoctor.ui.theme.PointUpperColor
import java.util.Calendar
import java.util.Date
import kotlin.math.min
import kotlin.math.roundToInt


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Graph(dataPressures: List<DataPressure>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ){
        val statusTimeIntervalButtons = remember{mutableStateOf<StatusTimeIntervalButtons>(StatusTimeIntervalButtons.WEEK)}
//        val isTalkBackEnabled by LocalContext.current.collectIsTalkbackEnabledAsState()
        val textMeasurer = rememberTextMeasurer()
        var offset by remember { androidx.compose.runtime.mutableFloatStateOf(0f) }
        var maxOffsetForGraph = remember { androidx.compose.runtime.mutableFloatStateOf(150f) }
        Canvas(
        modifier = Modifier
            .weight(1f)
            .height(150.dp)
            .scrollable(
                orientation = Orientation.Horizontal,
                state = rememberScrollableState { distance ->
                    offset += distance
                    min(distance, maxOffsetForGraph.value)
                }
            )
            .clipToBounds()
            /*.pointerInput(Unit){
                for (point in points) {
                    detectTapGestures { offset ->
                        if (isPointClicked(offset, point.offset)) {
                            clickedPointId = point.id
                        }
                    }
                }
            }*/
    ) {


        val height = size.height - 16.dp.toPx()
        val width = size.width
        drawLine(
            start = Offset(x = 0f, y = height - 0.5.dp.toPx()),
            end = Offset(x = width, y = height - 0.5.dp.toPx()),
            color = Black,
            strokeWidth = 1.dp.toPx()
        )
        drawLine(
            start = Offset(x = width - 0.5.dp.toPx(), y = 0f),
            end = Offset(x = width - 0.5.dp.toPx(), y = height),
            color = Black,
            strokeWidth = 1.dp.toPx()
        )
        drawLine(
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = width, y = 0f),
            color = Line200100Color,
            strokeWidth = 0.5.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
        )
        drawLine(
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = 0f, y = height),
            color = Line200100Color,
            strokeWidth = 0.5.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
        )
        drawLine(
            start = Offset(x = 0f, y = height / 4),
            end = Offset(x = width, y = height / 4),
            color = Line150Color,
            strokeWidth = 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
        )
        drawLine(
            start = Offset(x = 0f, y = 2 * height / 4),
            end = Offset(x = width, y = 2 * height / 4),
            color = Line200100Color,
            strokeWidth = 0.5.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
        )
        drawLine(
            start = Offset(x = 0f, y = 3 * height / 4),
            end = Offset(x = width, y = 3 * height / 4),
            color = Line50Color,
            strokeWidth = 1.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 10f, 10f), phase = 0f)
        )
        val pointsUpper = mutableListOf<Offset>()
        val pointsLower = mutableListOf<Offset>()
        val countPoints = dataPressures.size
        val relativeHeight = height / 200


        if (statusTimeIntervalButtons.value == StatusTimeIntervalButtons.WEEK) {
            //Добавление точек для недели
            val relativeWeekWidth = (width - 8.dp.toPx()) / 7
            val relativeHourWidth = relativeWeekWidth / 24
            val relativeMinuteWidth = relativeHourWidth / 60

            for (x in 0 until countPoints) {
                val yUpper = height - dataPressures[x].upperDataPressure.toFloat() * relativeHeight
                val yLower = height - dataPressures[x].lowerDataPressure.toFloat() * relativeHeight
                val dateFromBd = Calendar.getInstance()
                dateFromBd.time = dataPressures[x].dateAndTimeOfMeasurements
                val dateFromBdBetween = (Date(Date().year, Date().month, Date().date).time -
                        Date(
                            dataPressures[x].dateAndTimeOfMeasurements.year,
                            dataPressures[x].dateAndTimeOfMeasurements.month,
                            dataPressures[x].dateAndTimeOfMeasurements.date,
                        ).time) /
                        (60 * 60 * 24 * 1000)

                Log.i("Date", dateFromBdBetween.toString())
                Log.i("Date1", Date().time.toString())
                Log.i("Date2", dataPressures[x].dateAndTimeOfMeasurements.time.toString())

                val xWeekAndHourAndMinute = (6 - dateFromBdBetween) * relativeWeekWidth +
                        dataPressures[x].dateAndTimeOfMeasurements.hours * relativeHourWidth +
                        dataPressures[x].dateAndTimeOfMeasurements.minutes * relativeMinuteWidth

                pointsUpper.add(
                    Offset(
                        (offset.roundToInt() + xWeekAndHourAndMinute + 4.dp.toPx()),
                        yUpper
                    )
                )
                /*if (x == countPoints - 1) {
                    maxOffsetForGraph.value = pointsUpper[x].x
                }*/
                pointsLower.add(
                    Offset(
                        (offset.roundToInt() + xWeekAndHourAndMinute + 4.dp.toPx()),
                        yLower
                    )
                )
            }
            // Label Button для недели
            val widthForButtonLabelForWeek = width - 28.dp.toPx()
            val spaceBetweenButtonLabel = (widthForButtonLabelForWeek - 4.dp.toPx()) / 6
            var buttonLabelWeek: MutableList<String> = mutableListOf();
            val currentDate = Calendar.getInstance()
            for (i in 0..6) {
                val dayInLabel = currentDate[Calendar.DAY_OF_MONTH]
                val currentMonth = currentDate[Calendar.MONTH]
                val labelItem = "$dayInLabel.${currentMonth + 1}"
                buttonLabelWeek.add(labelItem)
                currentDate.add(Calendar.DAY_OF_MONTH, -1)

            }
            for (i in buttonLabelWeek.indices) {
                val textLayoutResult = textMeasurer.measure(
                    text = buttonLabelWeek[i],
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = LabelButtonInGraph
                    )
                )
                drawText(
                    textLayoutResult,
                    topLeft = Offset(
                        (6 - i) * spaceBetweenButtonLabel + 8.dp.toPx() + offset.roundToInt(),
                        height + 4.dp.toPx()
                    )
                )
            }
        }


        if (statusTimeIntervalButtons.value == StatusTimeIntervalButtons.DAY) {
            //Добавление точек для дня
            val paddingForPointInGraph = 4.dp.toPx()
            val relativeHourWidth = (width-2*paddingForPointInGraph) / 24
            val relativeMinuteWidth = (width-2*paddingForPointInGraph) / 1440

            for (x in 0 until countPoints) {
                val yUpper = height - dataPressures[x].upperDataPressure.toFloat() * relativeHeight
                val yLower = height - dataPressures[x].lowerDataPressure.toFloat() * relativeHeight
                val dateFromBd = Calendar.getInstance()
                dateFromBd.time = dataPressures[x].dateAndTimeOfMeasurements
                val xHourAndMinute = dateFromBd[Calendar.HOUR_OF_DAY] * relativeHourWidth +
                        dateFromBd[Calendar.MINUTE] * relativeMinuteWidth
                pointsUpper.add(Offset((xHourAndMinute + paddingForPointInGraph), yUpper))
                pointsLower.add(Offset((xHourAndMinute + paddingForPointInGraph), yLower))
            }

            // Label Button для дня
            val spaceBetweenButtonLabel = (width - 5.sp.toPx()) / 4
            val buttonLabelToday = listOf("0", "6", "12", "18", "0")
            for (i in buttonLabelToday.indices) {
                val textLayoutResult = textMeasurer.measure(
                    text = buttonLabelToday[i],
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = LabelButtonInGraph
                    )
                )
                drawText(
                    textLayoutResult,
                    topLeft = Offset(i * spaceBetweenButtonLabel, height + 4.dp.toPx())
                )
            }
        }




        drawPoints(
            points = pointsUpper,
            strokeWidth = 4.dp.toPx(),
            pointMode = PointMode.Points,
            color = PointUpperColor,
            cap = StrokeCap.Round
        )
        drawPoints(
            points = pointsLower,
            strokeWidth = 4.dp.toPx(),
            pointMode = PointMode.Points,
            color = PointLowerColor,
            cap = StrokeCap.Round
        )
        val (cubicPoints1, cubicPoints2) = getCubicPoints(pointsUpper)
        val path = Path()
        path.moveTo(pointsUpper.first().x, pointsUpper.first().y)
        for (i in 1 until pointsUpper.size) {
            path.cubicTo(
                cubicPoints1[i - 1].x,
                cubicPoints1[i - 1].y,
                cubicPoints2[i - 1].x,
                cubicPoints2[i - 1].y,
                pointsUpper[i].x,
                pointsUpper[i].y
            )
        }
        drawPath(
            path,
            LineUpperColor,
            style = Stroke(width = 5f)
        )

        val (cubicPoints1Lower, cubicPoints2Lower) = getCubicPoints(pointsLower)
        val pathLower = Path()
        pathLower.moveTo(pointsLower.first().x, pointsLower.first().y)
        for (i in 1 until pointsLower.size) {
            pathLower.cubicTo(
                cubicPoints1Lower[i - 1].x,
                cubicPoints1Lower[i - 1].y,
                cubicPoints2Lower[i - 1].x,
                cubicPoints2Lower[i - 1].y,
                pointsLower[i].x,
                pointsLower[i].y
            )
        }
        drawPath(
            pathLower,
            LineLowerColor,
            style = Stroke(width = 5f)
        )


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
                var xPlusOffet = 3.dp.toPx()
                if (i == 3) {
                    xPlusOffet += 2.dp.toPx()
                } else if (i == 4) {
                    xPlusOffet += 4.dp.toPx()
                }
                drawText(
                    textLayoutResult,
                    topLeft = Offset(0 + xPlusOffet, i * spaceBetweenLabelEnd - 2.dp.toPx())
                )
            }
        }
    }
}

@Preview(showBackground = true)
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
}

fun getCubicPoints(pointsData: List<Offset>): Pair<MutableList<Offset>, MutableList<Offset>> {
    val cubicPoints1 = mutableListOf<Offset>()
    val cubicPoints2 = mutableListOf<Offset>()

    for (i in 1 until pointsData.size) {
        cubicPoints1.add(
            Offset(
                (pointsData[i].x + pointsData[i - 1].x) / 2, pointsData[i - 1].y
            )
        )
        cubicPoints2.add(
            Offset(
                (pointsData[i].x + pointsData[i - 1].x) / 2, pointsData[i].y
            )
        )
    }
    return Pair(cubicPoints1, cubicPoints2)
}