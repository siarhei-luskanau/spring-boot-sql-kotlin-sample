package siarhei.luskanau.sql.example.demo.scheduler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*


@Component
class ScheduledComponent(
    @Autowired private val csvChecker: CsvChecker
) {

    // @Scheduled(fixedRate = 60_000)
    @Scheduled(cron = "\${scheduler.cron}")
    fun execute() {
        println("CsvChecker.check " + Date())
        csvChecker.check()
    }
}
