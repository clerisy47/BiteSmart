package com.app.bitesmart.data.dummyData

data class CaptureDateData(
    val nameOfDay: String,
    val year: String,
    val day: String,
    val monthName: String
)
fun getCaptureDateData(): List<CaptureDateData> {
    return listOf(
        CaptureDateData("Sun", "2023", "14", "Dec"),
        CaptureDateData("Mon", "2023", "15", "Dec"),
        CaptureDateData("Tue", "2023", "16", "Dec"),
        CaptureDateData("Wed", "2023", "17", "Dec"),
        CaptureDateData("Thu", "2023", "18", "Dec"),
        CaptureDateData("Fri", "2023", "19", "Dec"),
        CaptureDateData("Sat", "2023", "20", "Dec"),
        CaptureDateData("Sun", "2023", "21", "Dec"),
        CaptureDateData("Mon", "2023", "22", "Dec"),
        CaptureDateData("Tue", "2023", "23", "Dec")
    )
}