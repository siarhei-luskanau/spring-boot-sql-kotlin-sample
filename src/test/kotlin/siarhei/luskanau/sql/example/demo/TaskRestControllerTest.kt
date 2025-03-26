package siarhei.luskanau.sql.example.demo

import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import kotlin.test.Test

class TaskRestControllerTest {
    private val taskRepository by lazy { mock<TaskRepository>() }

    private val taskRestController by lazy { TaskRestController(repository = taskRepository) }

    @Test
    fun testTask1a() {
        // Given

        // When

        taskRestController.task1a()

        // Then
        verify(taskRepository).task1a(dateFrom = anyString(), dateTo = anyString(), soldCount = anyInt())
    }

    @Test
    fun testTask1b() {
        // Given

        // When

        taskRestController.task1b()

        // Then
        verify(taskRepository).task1b(dateFrom = anyString(), dateTo = anyString())
    }

    @Test
    fun testTask2() {
        // Given

        // When

        taskRestController.task2()

        // Then
        verify(taskRepository).task2()
    }

    @Test
    fun testTask3() {
        // Given

        // When

        taskRestController.task3()

        // Then
        verify(taskRepository).task3()
    }
}
