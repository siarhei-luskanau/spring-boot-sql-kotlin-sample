package siarhei.luskanau.sql.example.demo

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import kotlin.test.Test

@Testcontainers
@SpringBootTest
class DemoApplicationTests {
    @Test
    fun contextLoads() {
    }

    companion object {
        @Container
        @ServiceConnection
        var mySQLContainer: MySQLContainer<*> = MySQLContainer(DockerImageName.parse("mysql:latest"))
    }
}
