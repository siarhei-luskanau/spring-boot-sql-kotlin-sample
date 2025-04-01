package siarhei.luskanau.sql.example.demo.scheduler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class CsvChecker(
    @Autowired private val csvReader: CsvReader,
    @Autowired private val schedulerAction: SchedulerAction,
) {
    fun check(currentTime: ZonedDateTime) {
        val csvModelList = csvReader.read(path = "scheduler.csv")
        log.info("List<ScvModel>: {}", csvModelList)
        val isTriggeredEmpty =
            csvModelList.none { csvModel ->
                csvModel.dayOfWeekList.contains(currentTime.dayOfWeek) &&
                    csvModel.localTime.hour == currentTime.hour &&
                    csvModel.localTime.minute == currentTime.minute
            }
        if (!isTriggeredEmpty) {
            schedulerAction.execute()
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(CsvChecker::class.java)
    }
}
