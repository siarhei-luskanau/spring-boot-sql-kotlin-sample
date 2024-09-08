package siarhei.luskanau.sql.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import siarhei.luskanau.sql.example.demo.model.TestModel1
import siarhei.luskanau.sql.example.demo.model.TestModel2
import siarhei.luskanau.sql.example.demo.model.TestModel3

@RestController
class TaskRestController(
    @Autowired private val repository: TaskRepository
) {

    @GetMapping(path = ["/task1a"])  // GET http://localhost:8080/task1a
    fun task1a(): List<TestModel1> = repository.task1a(
        dateFrom = "2019-01-01",
        dateTo = "2023-01-01",
        soldCount = 10
    )

    @GetMapping(path = ["/task1b"])  // GET http://localhost:8080/task1b
    fun task1b(): List<TestModel1> = repository.task1b(
        dateFrom = "2020-01-01",
        dateTo = "2021-01-01"
    )

    @GetMapping(path = ["/task2"])  // GET http://localhost:8080/task2
    fun task2(): List<TestModel2> = repository.task2()

    @GetMapping(path = ["/task3"])  // GET http://localhost:8080/task3
    fun task3(): List<TestModel3> = repository.task3()
}
