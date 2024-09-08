package siarhei.luskanau.sql.example.demo.scheduler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class CsvChecker(
    @Autowired private val csvReader: CsvReader,
    @Autowired private val schedulerAction: SchedulerAction,
    @Value("\${scheduler.timezone}") private val timeZoneValue: String
) {


    fun check() {
        val csvModelList = csvReader.read()
        println("List<ScvModel>: $csvModelList")
        val triggered = getTriggeredScvModel(csvModelList)
        triggered.forEach { scvModel ->
            println("Action is triggered: $scvModel")
        }
        if (triggered.isNotEmpty()) {
            schedulerAction.execute()
        }
    }

    private fun getTriggeredScvModel(csvModelList: List<ScvModel>): List<ScvModel> {
        val currentTime = ZonedDateTime.now(ZoneId.of(timeZoneValue))
        println("CurrentTime: $currentTime")
        return csvModelList.filter { scvModel ->
            val timeString = scvModel.time.split(":")
            (scvModel.bitmask.toInt() shr currentTime.dayOfWeek.ordinal) % 2 == 1 &&
                    timeString[0].toInt() == currentTime.hour &&
                    timeString[1].toInt() == currentTime.minute
        }
    }
}