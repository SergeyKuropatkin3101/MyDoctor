package com.example.mydoctor.ViewModelProject

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import com.example.mydoctor.data.MainDb
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor (
    val mainDb: MainDb
) : ViewModel() {
    val statusTimeIntervalButtons = mutableStateOf<StatusTimeIntervalButtons>(StatusTimeIntervalButtons.WEEK)


    var offset = mutableFloatStateOf(0f)
    var maxAvailableOffsetForBackward = mutableFloatStateOf(0f)
    var maxAvailableOffsetForForward = mutableFloatStateOf(0f)
    var isScrollBackwardEnabled = true
    var isScrollForwardEnabled = true
    val offsetSelectedPoint = mutableStateOf<Offset?>(null)
    var isPopupShow = false


    /*fun isCheckOffsetPressAndPoint(){
        if (pointsUpper != null){
            for (i in pointsUpper.indices) {
                if (offsetSelectedPoint.value == pointsUpper[i]){
                    isPopupShow = true
                    Log.i("offsetSelectedPoint",offsetSelectedPoint.value.toString())
                }
            }
        }
    }*/

    val listPointForGraph:MutableList<PointForGraph> = mutableListOf()
    val listId = mutableListOf<Int>()
    fun transformationInPointForGraph(){
        Log.i("Begin","B")
        for (i in displayDataPressure.value.indices){
            if (displayDataPressure.value[i].id !in  listId) {
                listId.add(displayDataPressure.value[i].id!!)
                val point = PointForGraph(
                    upperDataPressure = displayDataPressure.value[i].upperDataPressure.toInt(),
                    lowerDataPressure = displayDataPressure.value[i].lowerDataPressure.toInt(),
                    pulse = displayDataPressure.value[i].pulse,
                    year = displayDataPressure.value[i].dateAndTimeOfMeasurements.year,
                    month = displayDataPressure.value[i].dateAndTimeOfMeasurements.month,
                    day = displayDataPressure.value[i].dateAndTimeOfMeasurements.date,
                    hour = displayDataPressure.value[i].dateAndTimeOfMeasurements.hours,
                    minute = displayDataPressure.value[i].dateAndTimeOfMeasurements.minutes,
                    note = displayDataPressure.value[i].noteOfMeasurements
                )
                listPointForGraph.add(point)
            }
        }

        Log.i("Begin","E")
    }
    val heightGraph = mutableFloatStateOf(0f)
    val widthGraph = mutableFloatStateOf(0f)
    val paddingHorizontalInGraph = mutableFloatStateOf(0f)
    private val currentDateYMD = Date(Date().year, Date().month, Date().date).time
    val offsetPointForShow = mutableFloatStateOf(0f)

    fun showWeekDataPressure() {

        getWeekDataPressures()
        transformationInPointForGraph()
        val countPoints = listPointForGraph.size
        val relativeHeight = heightGraph.floatValue / 200

        //Добавление точек для недели
        val relativeDayWidth = (widthGraph.floatValue - paddingHorizontalInGraph.floatValue) / 7
        val relativeHourWidth = relativeDayWidth / 24
        val relativeMinuteWidth = relativeHourWidth / 60

        for (x in 0 until countPoints) {
            listPointForGraph[x].yUpper =
                heightGraph.floatValue - listPointForGraph[x].upperDataPressure * relativeHeight
            listPointForGraph[x].yLower =
                heightGraph.floatValue - listPointForGraph[x].lowerDataPressure * relativeHeight
            val dateFromBdBetween = ( currentDateYMD -
                    Date(
                        listPointForGraph[x].year,
                        listPointForGraph[x].month,
                        listPointForGraph[x].day,
                    ).time
                    ) / (60 * 60 * 24 * 1000)


            val xWeekAndHourAndMinute = (6 - dateFromBdBetween) * relativeDayWidth +
                    listPointForGraph[x].hour * relativeHourWidth +
                    listPointForGraph[x].minute * relativeMinuteWidth

            listPointForGraph[x].x = offset.floatValue + xWeekAndHourAndMinute + offsetPointForShow.floatValue

            Log.i("Begin",listPointForGraph[x].x.toString())
        }
        /*
                // Label Button для недели
                val sizeOneLabel = 4 * 10.sp.toPx()
                val widthForButtonLabelForWeek = width - sizeOneLabel - paddingHorizontalInGraph
                val spaceBetweenButtonLabel = widthForButtonLabelForWeek / 6
                var buttonLabelWeek: MutableList<String> = mutableListOf();
                val currentDate = Calendar.getInstance()
                var firstDateInBd = Date(
                    dataPressures[0].dateAndTimeOfMeasurements.year,
                    dataPressures[0].dateAndTimeOfMeasurements.month,
                    dataPressures[0].dateAndTimeOfMeasurements.date,
                ).time

                do {
                    val dayInLabel = currentDate[Calendar.DAY_OF_MONTH]
                    val currentMonth = currentDate[Calendar.MONTH]
                    val labelItem = "$dayInLabel.${currentMonth + 1}"
                    buttonLabelWeek.add(labelItem)
                    currentDate.add(Calendar.DAY_OF_MONTH, -1)
                } while (firstDateInBd <= currentDate.timeInMillis)


                maxAvailableOffsetForBackward.floatValue =
                    -((7 - buttonLabelWeek.size) * spaceBetweenButtonLabel - (paddingHorizontalInGraph) / 2 + offset)
                isScrollBackwardEnabled =
                    (7 - buttonLabelWeek.size) * spaceBetweenButtonLabel - (paddingHorizontalInGraph) / 2 + offset <= 0
                maxAvailableOffsetForForward.floatValue = -offset
                isScrollForwardEnabled = maxAvailableOffsetForForward.floatValue <= 0
                Log.i("maxAvailableOffsetForBackward", maxAvailableOffsetForForward.floatValue.toString())
                    Log.i("isScrollBackwardEnabled", isScrollForwardEnabled.toString())*/

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

}