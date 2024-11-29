package com.example.mydoctor.ViewModelProject

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydoctor.data.DataPressure
import com.example.mydoctor.data.DataPressureDao
import kotlinx.coroutines.launch

class PressureViewModel (private val dataPressureDao: DataPressureDao) : ViewModel() {

    private val _dataPressure = mutableStateOf<List<DataPressure>>(emptyList())
    val dataPressure: State<List<DataPressure>> get() = _dataPressure

    init {
        loadDataPressures()
    }

    private fun loadDataPressures() {
        viewModelScope.launch {
            _dataPressure.value = dataPressureDao.getDatesToday()
        }
    }

//    fun addDataPressure(taskName: String) {
//        viewModelScope.launch {
//            dataPressureDao.insert(DataPressure())
//            loadDataPressures() // Обновляем список задач после добавления
//        }
//    }

//    fun deleteTask(taskId: Int) {
//        viewModelScope.launch {
//            taskDao.delete(taskId)
//            loadTasks() // Обновляем список задач после удаления
//        }
//    }
}