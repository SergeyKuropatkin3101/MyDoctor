package com.example.mydoctor.ViewModelProject


data class PointForGraph(
    var x: Float = 0f,          // верхнее давление (Систолическое)
    var yUpper: Float = 0f,
    var yLower: Float = 0f,
    var upperDataPressure: Int = 0,          // верхнее давление (Систолическое)
    var lowerDataPressure: Int = 0,          // нижнее давление (Диастолическое)
    var pulse: String = "",                      // пульс
    var year: Int = 0,    // дата и время измерений
    var month: Int = 0,    // дата и время измерений
    var day: Int = 0,    // дата и время измерений
    var hour: Int = 0,    // дата и время измерений
    var minute: Int = 0,    // дата и время измерений
    var note: String = ""
)