package siarhei.luskanau.sql.example.demo.scheduler

import org.junit.jupiter.api.Assertions
import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeParseException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CsvParserTest {
    private val csvParser by lazy { CsvParser() }

    @Test
    fun testParseValidInputAllDays() {
        // Given
        val time = "14:30" // 2:30 PM
        val bitmask = "7F" // All days

        // When
        val result = csvParser.parse(time = time, bitmask = bitmask)

        // Then

        // Validate the parsed time
        assertEquals(expected = LocalTime.of(14, 30), actual = result.localTime)

        // Validate the parsed days of the week
        assertEquals(
            expected =
                listOf(
                    DayOfWeek.MONDAY,
                    DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY,
                    DayOfWeek.FRIDAY,
                    DayOfWeek.SATURDAY,
                    DayOfWeek.SUNDAY,
                ),
            actual = result.dayOfWeekList,
        )
    }

    @Test
    fun testParseValidInputMondayWednesday() {
        // Given
        val time = "14:30" // 2:30 PM
        val bitmask = "5" // This corresponds to Monday and Wednesday (in binary)

        // When
        val result = csvParser.parse(time = time, bitmask = bitmask)

        // Then

        // Validate the parsed time
        assertEquals(expected = LocalTime.of(14, 30), result.localTime)

        // Validate the parsed days of the week
        assertEquals(
            expected = listOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
            actual = result.dayOfWeekList,
        )
    }

    @Test
    fun testParseEmptyBitmask() {
        // Given
        val time = "08:00" // 8:00 AM
        val bitmask = "0" // This means no days are selected.

        // When
        val result = csvParser.parse(time = time, bitmask = bitmask)

        // Then

        // Validate the parsed time
        assertEquals(LocalTime.of(8, 0), result.localTime)

        // Validate that no days are selected
        assertTrue(actual = result.dayOfWeekList.isEmpty())
    }

    @Test
    fun testParseSingleDayBitmask() {
        // Given
        val time = "10:00" // 10:00 AM
        val bitmask = "10" // This corresponds to only Friday being selected.

        // When
        val result = csvParser.parse(time = time, bitmask = bitmask)

        // Then

        // Validate the parsed time
        assertEquals(expected = LocalTime.of(10, 0), actual = result.localTime)

        // Validate that only Monday is selected
        assertEquals(expected = listOf(DayOfWeek.FRIDAY), actual = result.dayOfWeekList)
    }

    @Test
    fun testParseInvalidBitmask() {
        // Given
        val time = "12:00" // 12:00 PM
        val invalidBitmask = "ZZ" // Invalid bitmask (non-hexadecimal characters)

        // When
        Assertions.assertThrows(NumberFormatException::class.java) {
            csvParser.parse(time = time, bitmask = invalidBitmask)
        }

        // Then
    }

    @Test
    fun testParseInvalidTimeFormat() {
        // Given
        val invalidTime = "14:60" // Invalid time format (60 minutes is not valid)

        // When
        Assertions.assertThrows(DateTimeParseException::class.java) {
            csvParser.parse(time = invalidTime, bitmask = "1F")
        }

        // Then
    }
}
