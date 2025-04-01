package siarhei.luskanau.sql.example.demo.scheduler

import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime
import kotlin.test.Test

class CsvCheckerTest {
    private val csvReader by lazy { mock<CsvReader>() }

    private val schedulerAction by lazy { mock<SchedulerAction>() }

    private val csvChecker by lazy { CsvChecker(csvReader = csvReader, schedulerAction = schedulerAction) }

    @Test
    fun testCheckTriggersSchedulerActionWhenMatchFound() {
        // Given
        val currentTime = ZonedDateTime.of(2025, 3, 28, 15, 30, 0, 0, ZonedDateTime.now().zone)

        val csvModel =
            CsvModel(
                localTime = LocalTime.of(15, 30),
                dayOfWeekList = listOf(DayOfWeek.FRIDAY),
            )

        val csvModelList = listOf(csvModel)
        whenever(csvReader.read(path = "scheduler.csv")).thenReturn(csvModelList)

        // When
        csvChecker.check(currentTime)

        // Then
        verify(schedulerAction, times(1)).execute()
    }

    @Test
    fun testCheckDoesNotTriggerSchedulerActionWhenNoMatch() {
        // Given
        val currentTime = ZonedDateTime.of(2025, 3, 28, 10, 0, 0, 0, ZonedDateTime.now().zone)

        val csvModel =
            CsvModel(
                localTime = LocalTime.of(15, 30),
                dayOfWeekList = listOf(DayOfWeek.MONDAY),
            )

        val csvModelList = listOf(csvModel)
        whenever(csvReader.read(path = "scheduler.csv")).thenReturn(csvModelList)

        // When
        csvChecker.check(currentTime)

        // Then
        verify(schedulerAction, times(0)).execute()
    }
}
