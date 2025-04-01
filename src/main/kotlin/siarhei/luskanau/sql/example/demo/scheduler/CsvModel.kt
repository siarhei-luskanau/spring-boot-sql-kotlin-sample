package siarhei.luskanau.sql.example.demo.scheduler

import java.time.DayOfWeek
import java.time.LocalTime

data class CsvModel(
    val localTime: LocalTime,
    val dayOfWeekList: List<DayOfWeek>,
)
