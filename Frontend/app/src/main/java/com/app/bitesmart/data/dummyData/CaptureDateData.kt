package com.app.bitesmart.data.dummyData

data class CaptureDateData(
    val nameOfDay: String,
    val year: String,
    val day: String,
    val monthName: String
)
fun getCaptureDateData(): List<CaptureDateData> {
    return listOf(
        CaptureDateData("Mon", "2025", "14", "Jan"),
        CaptureDateData("Mon", "2025", "14", "Jan"),
        CaptureDateData("Mon", "2025", "14", "Jan"),
        CaptureDateData("Sun", "2025", "13", "Jan"),
        CaptureDateData("Sat", "2025", "12", "Jan"),
        CaptureDateData("Fri", "2025", "11", "Jan"),
        CaptureDateData("Thu", "2025", "10", "Jan"),
        CaptureDateData("Wed", "2025", "09", "Jan"),
        CaptureDateData("Tue", "2024", "23", "Dec"),
        CaptureDateData("Mon", "2024", "22", "Dec"),
        CaptureDateData("Sun", "2024", "21", "Dec"),
        CaptureDateData("Sat", "2024", "20", "Dec"),
        CaptureDateData("Fri", "2024", "19", "Dec"),
        CaptureDateData("Thu", "2024", "18", "Dec"),
        CaptureDateData("Wed", "2024", "17", "Dec"),
        CaptureDateData("Tue", "2024", "16", "Dec"),
        CaptureDateData("Mon", "2024", "15", "Dec"),
        CaptureDateData("Sun", "2024", "14", "Dec")

    )
}