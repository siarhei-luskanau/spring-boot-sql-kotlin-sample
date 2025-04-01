package siarhei.luskanau.sql.example.demo.scheduler

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import kotlin.test.Test

class ScheduledComponentTest {
    private val csvChecker by lazy { mock<CsvChecker>() }

    private val scheduledComponent by lazy { ScheduledComponent(csvChecker, "UTC") }

    @Test
    fun testExecute() {
        // Given

        // When

        scheduledComponent.execute()

        // Then
        verify(csvChecker).check(currentTime = any())
    }
}
