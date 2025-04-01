package siarhei.luskanau.sql.example.demo.scheduler

import org.junit.jupiter.api.Assertions
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever
import java.time.DayOfWeek
import java.time.LocalTime
import kotlin.test.Test
import kotlin.test.assertEquals

class CsvReaderTest {
    private val csvParser by lazy { mock<CsvParser>() }

    private val csvReader by lazy { CsvReader(csvParser = csvParser) }

    @Test
    fun testRead() {
        // Given
        val filename = "scheduler_test.csv"
        val csvModel =
            CsvModel(
                LocalTime.of(10, 8),
                listOf(DayOfWeek.FRIDAY),
            )
        whenever(csvParser.parse("10:08", "10")).thenReturn(csvModel)

        // When
        val actual = csvReader.read(filename)

        // Then
        assertEquals(expected = listOf(csvModel), actual = actual)
        verify(csvParser).parse("10:08", "10")
    }

    @Test
    fun testReadFailure() {
        // Given
        val filename = "missing.csv"

        // When
        val result =
            Assertions.assertThrows(
                CsvFileException::class.java,
            ) { csvReader.read(path = filename) }

        // Then
        assertEquals(expected = filename, actual = result.message)
    }
}
