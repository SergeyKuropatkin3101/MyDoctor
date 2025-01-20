package com.example.mydoctor.viewModelProject

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydoctor.data.DataPressure
import com.example.mydoctor.data.MainDb
import com.example.mydoctor.ui.theme.Grey300
import com.example.mydoctor.ui.theme.White
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import kotlin.math.abs


private const val MAXHEIGHTGRAPH = 200

@HiltViewModel
class MainScreenViewModel @Inject constructor (
    private val mainDb: MainDb
) : ViewModel() {
    private val displayDataPressure = mutableStateOf<List<DataPressure>>(emptyList())
    private var periodOnGraph = TimePeriod.DAY

    fun isTimePeriodDay(): Boolean{
        return periodOnGraph == TimePeriod.DAY
    }
    fun isTimePeriodWeek(): Boolean{
        return periodOnGraph == TimePeriod.WEEK
    }
    fun isTimePeriodMonth(): Boolean{
        return periodOnGraph == TimePeriod.MONTH
    }

    val periodOnMainScreen = mutableStateOf<String>("Сегодня")
    val pressureOnMainScreen = mutableStateOf<String>("")
    val pulseOnMainScreen = mutableStateOf<String>("")

    val noteOnMainScreen = mutableStateOf<String>("")
    val isShowDefaultNoteOnMainScreen = mutableStateOf<Boolean>(true)
    val isShowNote = mutableStateOf<Boolean>(false)
    val dateNoteOnMainScreen = mutableStateOf<String>("")

    private val dataFormatForPeriod = SimpleDateFormat("dd MMMM")
    private val dataFormatForNote = SimpleDateFormat("dd.MM HH:mm")

    private fun updateTextDataOnMainScreen() {
        if (isTimePeriodDay())
            periodOnMainScreen.value = "Сегодня"
        else
            if (isTimePeriodWeek()) {
                val dateToday = Calendar.getInstance()
                periodOnMainScreen.value = " — " + dataFormatForPeriod.format(dateToday.timeInMillis)
                dateToday.add(Calendar.DAY_OF_MONTH, -7)
                periodOnMainScreen.value = dataFormatForPeriod.format(dateToday.timeInMillis) + periodOnMainScreen.value

            }

            else {
                val dateToday = Calendar.getInstance()
                periodOnMainScreen.value = " — " + dataFormatForPeriod.format(dateToday.timeInMillis)
                dateToday.add(Calendar.DAY_OF_MONTH, -42)
                periodOnMainScreen.value = dataFormatForPeriod.format(dateToday.timeInMillis) + periodOnMainScreen.value
            }

        if(listPointForGraph.size == 1){
            isShowDefaultNoteOnMainScreen.value = false
            isShowNote.value = true
            pressureOnMainScreen.value = "${listPointForGraph[0].upperDataPressure} / ${listPointForGraph[0].lowerDataPressure}"
            pulseOnMainScreen.value = maxAndMinPulse(listPointForGraph," - ")
            if (listPointForGraph[0].note.isNotEmpty())
                noteOnMainScreen.value = listPointForGraph[0].note
        }
        else if (listPointForGraph.size > 1){
            isShowDefaultNoteOnMainScreen.value = false
            isShowNote.value = false
            pressureOnMainScreen.value = "${maxAndMinUpperPressure(listPointForGraph, " - ")} / ${maxAndMinLowerPressure(listPointForGraph," - ")}"
            pulseOnMainScreen.value = maxAndMinPulse(listPointForGraph," - ")
        }
    }




    val enabledPopupState = mutableStateOf(true)
    suspend fun delayPopup() {
        delay(10000)
        enabledPopupState.value = false
    }

    fun turnOffEnabledPopup() {
        enabledPopupState.value = false
    }
    private fun getDayDataPressuresForDisplay(date: Long) {
        viewModelScope.launch {
            displayDataPressure.value = mainDb.dao.getDatesDay(date)
        }
    }


    private fun getAllDataPressures() {
        viewModelScope.launch {
            displayDataPressure.value = mainDb.dao.getAllDates()
        }
    }
    var offset = mutableFloatStateOf(0f)
    var maxAvailableOffsetForBackward = mutableFloatStateOf(0f)
    var maxAvailableOffsetForForward = mutableFloatStateOf(0f)
    var isScrollBackwardEnabled = true
    var isScrollForwardEnabled = true
    val offsetSelectedPoint = mutableStateOf<Offset?>(null)
    val isPopupShow = mutableStateOf(false)
    val pressurePopup = mutableStateOf("")
    val pressureUpperPopup = mutableStateOf("")
    val pressureLowerPopup = mutableStateOf("")
    val pulsePopup = mutableStateOf("")
    val datePopup = mutableStateOf("")
    val colorDayButtonTime = mutableStateOf(Grey300)
    val colorWeekButtonTime = mutableStateOf(White)
    val colorMonthButtonTime = mutableStateOf(White)



    var listPointForGraph = mutableStateListOf<PointForGraph>()
    private val dayDataPressure = mutableStateListOf<PointForGraph>()

    private val listId = mutableListOf<Int>()
    private fun transformationInPointForGraph(){
        for (i in displayDataPressure.value.indices){
            if (displayDataPressure.value[i].id !in  listId) {
                listId.add(displayDataPressure.value[i].id!!)
                val point = PointForGraph(
                    upperDataPressure = displayDataPressure.value[i].upperDataPressure.toInt(),
                    lowerDataPressure = displayDataPressure.value[i].lowerDataPressure.toInt(),
                    pulse = displayDataPressure.value[i].pulse,
                    dateAndTimeOfMeasurements = displayDataPressure.value[i].dateAndTimeOfMeasurements,
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
    }
    val heightGraph = mutableFloatStateOf(0f)
    val widthGraph = mutableFloatStateOf(0f)
    private val currentDateYMD = Date(Date().year, Date().month, Date().date).time
    val onePxFromDp = mutableFloatStateOf(0f)
    private val paddingHorizontalInGraph = derivedStateOf { 14*onePxFromDp.floatValue }
    private val sizeOneLabel = derivedStateOf { 40*onePxFromDp.floatValue }
    private val offsetPointForShow = derivedStateOf { 6*onePxFromDp.floatValue }
    private val spaceBetweenBottomLabel = derivedStateOf {(widthGraph.floatValue - sizeOneLabel.value - paddingHorizontalInGraph.value)/6}

    var listLabelForGraph = mutableStateListOf<LabelBottomForGraph>()

    fun clearShowData(){
        listId.clear()
        listPointForGraph.clear()
        listLabelForGraph.clear()
        offset.floatValue = 0f
    }

    private fun updateColorButtonTime(period: TimePeriod){
        for (i in 0 ..2){
            if (period == TimePeriod.DAY){
                colorDayButtonTime.value = Grey300
                colorWeekButtonTime.value = White
                colorMonthButtonTime.value = White
            }
            else if(period == TimePeriod.WEEK){
                colorDayButtonTime.value = White
                colorWeekButtonTime.value = Grey300
                colorMonthButtonTime.value = White
            }
            else{
                colorDayButtonTime.value = White
                colorWeekButtonTime.value = White
                colorMonthButtonTime.value = Grey300
            }
        }
    }


    fun showDayDataPressure() {
        periodOnGraph = TimePeriod.DAY
        updateColorButtonTime(periodOnGraph)
        clearShowData()
        val date = Calendar.getInstance()
        date.set(Calendar.HOUR_OF_DAY, 0)
        date.set(Calendar.MINUTE, 0)
        date.set(Calendar.SECOND, 0)
        date.set(Calendar.MILLISECOND, 0)
        val today = date.timeInMillis
        getDayDataPressuresForDisplay(today)
        transformationInPointForGraph()
        updateTextDataOnMainScreen()
        val relativeHeight = heightGraph.floatValue / MAXHEIGHTGRAPH
        //Добавление точек для дня
        val paddingForPointInGraph = 4*onePxFromDp.floatValue
        val relativeHourWidth = (widthGraph.floatValue - 2 * paddingForPointInGraph) / 24
        val relativeMinuteWidth = (widthGraph.floatValue - 2 * paddingForPointInGraph) / 1440
        for (i in 0 until listPointForGraph.size) {
            listPointForGraph[i].yUpper =
                heightGraph.floatValue - listPointForGraph[i].upperDataPressure * relativeHeight
            listPointForGraph[i].yLower =
                heightGraph.floatValue - listPointForGraph[i].lowerDataPressure * relativeHeight
            listPointForGraph[i].x = listPointForGraph[i].hour * relativeHourWidth +
                    listPointForGraph[i].minute * relativeMinuteWidth
        }


        // Label Button для дня
        val spaceBetweenButtonLabel = (widthGraph.floatValue - 7*onePxFromDp.floatValue - paddingHorizontalInGraph.value) / 4
        val buttonLabelToday = listOf("0", "6", "12", "18", "0")
        for (i in buttonLabelToday.indices) {
            listLabelForGraph.add(
                LabelBottomForGraph(
                i * spaceBetweenButtonLabel + (paddingHorizontalInGraph.value) / 2,
                heightGraph.floatValue + 4*onePxFromDp.floatValue,
                buttonLabelToday[i]
                )
            )
        }
    }


    fun showWeekDataPressure() {
        periodOnGraph = TimePeriod.WEEK
        updateColorButtonTime(periodOnGraph)
        clearShowData()
        getAllDataPressures()
        transformationInPointForGraph()
        updateTextDataOnMainScreen()
        val relativeHeight = heightGraph.floatValue / MAXHEIGHTGRAPH

        //Добавление точек для недели
        val relativeDayWidth = (widthGraph.floatValue - paddingHorizontalInGraph.value) / 7
        val relativeHourWidth = relativeDayWidth / 24
        val relativeMinuteWidth = relativeHourWidth / 60

        for (i in listPointForGraph.indices) {
            listPointForGraph[i].yUpper =
                heightGraph.floatValue - listPointForGraph[i].upperDataPressure * relativeHeight
            listPointForGraph[i].yLower =
                heightGraph.floatValue - listPointForGraph[i].lowerDataPressure * relativeHeight
            val dateFromBdBetween = (currentDateYMD -
                    Date(
                        listPointForGraph[i].year,
                        listPointForGraph[i].month,
                        listPointForGraph[i].day,
                    ).time
                    ) / (60 * 60 * 24 * 1000)
            val xWeekAndHourAndMinute = (6 - dateFromBdBetween) * relativeDayWidth +
                    listPointForGraph[i].hour * relativeHourWidth +
                    listPointForGraph[i].minute * relativeMinuteWidth

            listPointForGraph[i].x =
                xWeekAndHourAndMinute + offsetPointForShow.value
        }


        // Label Button для недели
        if (listPointForGraph.size != 0) {
            var i = 0
            var firstDateInBd = Date(
                listPointForGraph[0].year,
                listPointForGraph[0].month,
                listPointForGraph[0].day,
            ).time
            val dayWeekAgo = Calendar.getInstance()
            dayWeekAgo.add(Calendar.DAY_OF_MONTH, -6)
            if (dayWeekAgo.timeInMillis <= firstDateInBd){
                firstDateInBd = dayWeekAgo.timeInMillis
            }

            val currentDate = Calendar.getInstance()
            do {
                val dayInLabel = currentDate[Calendar.DAY_OF_MONTH]
                val currentMonth = currentDate[Calendar.MONTH]
                val labelItem = "$dayInLabel.${currentMonth + 1}"
                listLabelForGraph.add(LabelBottomForGraph(
                    x = (6 - i) * spaceBetweenBottomLabel.value + (paddingHorizontalInGraph.value) / 2 + 6*onePxFromDp.floatValue,
                    y = heightGraph.floatValue + 4*onePxFromDp.floatValue,
                    label = labelItem))
                currentDate.add(Calendar.DAY_OF_MONTH, -1)
                i+=1
            } while (firstDateInBd <= currentDate.timeInMillis)
        }
    }


    fun showMonthDataPressure() {
        periodOnGraph = TimePeriod.MONTH
        updateColorButtonTime(periodOnGraph)
        clearShowData()
        getAllDataPressures()
        transformationInPointForGraph()
        updateTextDataOnMainScreen()
        val relativeHeight = heightGraph.floatValue / MAXHEIGHTGRAPH

        //Добавление точек для месяца
        val relativeDayWidth = (widthGraph.floatValue - paddingHorizontalInGraph.value) / 42
        val relativeHourWidth = relativeDayWidth / 24
        val relativeMinuteWidth = relativeHourWidth / 60

        for (i in listPointForGraph.indices) {
            listPointForGraph[i].yUpper =
                heightGraph.floatValue - listPointForGraph[i].upperDataPressure * relativeHeight
            listPointForGraph[i].yLower =
                heightGraph.floatValue - listPointForGraph[i].lowerDataPressure * relativeHeight
            val dateFromBdBetween = (currentDateYMD -
                    Date(
                        listPointForGraph[i].year,
                        listPointForGraph[i].month,
                        listPointForGraph[i].day,
                    ).time
                    ) / ( 24 * 60 * 60 * 1000)


            val xWeekAndHourAndMinute = (41 - dateFromBdBetween) * relativeDayWidth +
                    listPointForGraph[i].hour * relativeHourWidth +
                    listPointForGraph[i].minute * relativeMinuteWidth

            listPointForGraph[i].x =
                xWeekAndHourAndMinute + offsetPointForShow.value
        }


        // Label Button для месяца
        if (listPointForGraph.size != 0) {
            var i = 0
            var firstDateInBd = Date(
                listPointForGraph[0].year,
                listPointForGraph[0].month,
                listPointForGraph[0].day,
            ).time
            val dayWeekAgo = Calendar.getInstance()
            dayWeekAgo.add(Calendar.DAY_OF_MONTH, -42)
            if (dayWeekAgo.timeInMillis <= firstDateInBd){
                firstDateInBd = dayWeekAgo.timeInMillis
            }

            val currentDate = Calendar.getInstance()
            var lenghtAllLabel = 0
            do {
                val dayInLabel = currentDate[Calendar.DAY_OF_MONTH]
                val currentMonth = currentDate[Calendar.MONTH]
                val labelItem = "$dayInLabel.${currentMonth + 1}"
                if (labelItem.length < 5){
                    lenghtAllLabel += 5-labelItem.length
                }
                listLabelForGraph.add(LabelBottomForGraph(
                    x = (6 - i) * spaceBetweenBottomLabel.value + (paddingHorizontalInGraph.value) / 2 + lenghtAllLabel*onePxFromDp.floatValue + 6*onePxFromDp.floatValue,
                    y = heightGraph.floatValue + 4*onePxFromDp.floatValue,
                    label = labelItem))
                currentDate.add(Calendar.DAY_OF_MONTH, -7)
                i+=1
            } while (firstDateInBd <= currentDate.timeInMillis)
        }
    }

    fun changeOffsetX(){
        maxAvailableOffsetForBackward.floatValue =
            -((7 - listLabelForGraph.size) * spaceBetweenBottomLabel.value - (paddingHorizontalInGraph.value) / 2 + offset.floatValue)
        isScrollBackwardEnabled = maxAvailableOffsetForBackward.floatValue >= 0
        maxAvailableOffsetForForward.floatValue = -offset.floatValue
        isScrollForwardEnabled = maxAvailableOffsetForForward.floatValue <= 0
    }

    fun isCheckOffsetPressAndPoint(){
        if (listPointForGraph.size != 0){
            for (i in listPointForGraph.indices) {
                if (abs(offsetSelectedPoint.value!!.x - (listPointForGraph[i].x + offset.floatValue)).dp < 10.dp) {
                    isPopupShow.value = true
                    if (periodOnGraph != TimePeriod.DAY){
                        dayDataPressure.clear()
                        var day = Date(
                            listPointForGraph[i].year,
                            listPointForGraph[i].month,
                            listPointForGraph[i].day,
                        ).time
                        getDayDataPressures(day)

                    }
                    updateTextPopup(i)

                }


            }
        }
    }

    private fun getDayDataPressures(day: Long) {
        for (i in listPointForGraph.indices) {
            if(listPointForGraph[i].dateAndTimeOfMeasurements!!.time in day..day+86400000)
                dayDataPressure.add(listPointForGraph[i])
        }
    }

    fun isCheckOnePopup(): Boolean{
        return (periodOnGraph == TimePeriod.DAY || dayDataPressure.size <= 1)
    }

    private val dataFormatForPopupMultiple = SimpleDateFormat("dd MMMM yyyy г.")
    private val dataFormatForPopupOne = SimpleDateFormat("Сегодня HH:mm")
    private fun updateTextPopup(indexPoint: Int){
        if (isCheckOnePopup()){
            pressurePopup.value = listPointForGraph[indexPoint].upperDataPressure.toString()+"/"+listPointForGraph[indexPoint].lowerDataPressure.toString()
            if (listPointForGraph[indexPoint].pulse.isNotEmpty())
                pulsePopup.value = listPointForGraph[indexPoint].pulse
            else
                pulsePopup.value = "—"
            if (periodOnGraph == TimePeriod.DAY)
                datePopup.value = dataFormatForPopupOne.format(listPointForGraph[indexPoint].dateAndTimeOfMeasurements!!)
            else
                datePopup.value = dataFormatForPopupMultiple.format(listPointForGraph[indexPoint].dateAndTimeOfMeasurements!!)

        }
        else{
            pressureUpperPopup.value = maxAndMinUpperPressure(dayDataPressure," — ")
            pressureLowerPopup.value = maxAndMinLowerPressure(dayDataPressure," — ")
            pulsePopup.value = maxAndMinPulse(dayDataPressure," — ")

            datePopup.value = dataFormatForPopupMultiple.format(listPointForGraph[indexPoint].dateAndTimeOfMeasurements!!)

        }
        isShowNote.value = true
        if (listPointForGraph[indexPoint].note.isNotEmpty()){
            noteOnMainScreen.value = listPointForGraph[indexPoint].note
            dateNoteOnMainScreen.value = dataFormatForNote.format(listPointForGraph[indexPoint].dateAndTimeOfMeasurements!!.time)
        }
        else
            isShowNote.value = false

    }

    private fun maxAndMinUpperPressure(dataPressure: SnapshotStateList<PointForGraph>, sep: String): String {
        return dataPressure.maxOf { it.upperDataPressure }.toString()+ sep +
                dataPressure.minOf { it.upperDataPressure }.toString()
    }


    private fun maxAndMinLowerPressure(dataPressure: SnapshotStateList<PointForGraph>, sep: String): String {
        return dataPressure.maxOf { it.lowerDataPressure }.toString()+ sep +
                dataPressure.minOf { it.lowerDataPressure }.toString()
    }
    private fun maxAndMinPulse(dataPressure: SnapshotStateList<PointForGraph>, sep: String): String {
        var countPulse = 0
        for (i in dataPressure.indices){
            if (dataPressure[i].pulse.isNotEmpty())
                countPulse += 1
        }
        return if (countPulse>1)
            dataPressure.maxOf { it.pulse }.toString()+ sep +
                    dataPressure.mapNotNull { it.pulse.toIntOrNull() }
                        .minOrNull()
                        ?.toString()
        else if (countPulse==1)
            dataPressure.map { it.pulse }
                .firstOrNull { it.isNotEmpty() }.toString()
        else
            "—"

    }

    fun isChecklistMore2(): Boolean{
        return listPointForGraph.size > 1
    }

    fun getCubicPoints(): Pair<Pair<MutableList<Offset>, MutableList<Offset>>,Pair<MutableList<Offset>, MutableList<Offset>>> {
        val cubicPoints1Upper = mutableListOf<Offset>()
        val cubicPoints2Upper = mutableListOf<Offset>()
        val cubicPoints1Lower = mutableListOf<Offset>()
        val cubicPoints2Lower = mutableListOf<Offset>()

        for (i in 1 until listPointForGraph.size) {
            cubicPoints1Upper.add(
                Offset(
                    (listPointForGraph[i].x + listPointForGraph[i - 1].x) / 2, listPointForGraph[i - 1].yUpper
                )
            )
            cubicPoints2Upper.add(
                Offset(
                    (listPointForGraph[i].x + listPointForGraph[i - 1].x) / 2, listPointForGraph[i].yUpper
                )
            )
            cubicPoints1Lower.add(
                Offset(
                    (listPointForGraph[i].x + listPointForGraph[i - 1].x) / 2, listPointForGraph[i - 1].yLower
                )
            )
            cubicPoints2Lower.add(
                Offset(
                    (listPointForGraph[i].x + listPointForGraph[i - 1].x) / 2, listPointForGraph[i].yLower
                )
            )
        }
        return Pair(Pair(cubicPoints1Upper, cubicPoints2Upper),Pair(cubicPoints1Lower, cubicPoints2Lower))
    }

}