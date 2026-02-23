package siarhei.luskanau.sql.example.demo.scheduler

import org.springframework.stereotype.Component
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Component
class CsvParser {
    fun parse(
        time: String,
        bitmask: String,
    ): CsvModel {
        val localTime = LocalTime.parse(time, LOCAL_TIME_FORMATTER)

        @Suppress("MagicNumber")
        val scheduleBitmask = bitmask.toByte(16)
        val dayOfWeekList =
            DayOfWeek.entries
                .filter { dayOfWeek -> (1 shl dayOfWeek.ordinal and scheduleBitmask.toInt()) != 0 }

        return CsvModel(localTime = localTime, dayOfWeekList = dayOfWeekList)
    }

    companion object {
        private val LOCAL_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    }
}
