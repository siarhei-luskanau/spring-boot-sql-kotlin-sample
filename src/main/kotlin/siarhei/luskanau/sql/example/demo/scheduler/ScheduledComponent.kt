package siarhei.luskanau.sql.example.demo.scheduler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.ZoneId
import java.time.ZonedDateTime

@Component
class ScheduledComponent(
    @Autowired private val csvChecker: CsvChecker,
    @Value("\${scheduler.timezone}") private val timeZoneValue: String,
) {
    // @Scheduled(fixedRate = 60_000)
    @Scheduled(cron = "\${scheduler.cron}")
    fun execute() {
        val currentTime = ZonedDateTime.now(ZoneId.of(timeZoneValue))
        log.info("CsvChecker.check: {}", currentTime)
        csvChecker.check(currentTime = currentTime)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ScheduledComponent::class.java)
    }
}
