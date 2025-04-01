package siarhei.luskanau.sql.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import javax.sql.DataSource

@Configuration
@EnableJdbcRepositories
class TaskJdbcConfiguration : AbstractJdbcConfiguration() {
    @Bean
    fun namedParameterJdbcTemplate(operations: JdbcOperations): NamedParameterJdbcTemplate = NamedParameterJdbcTemplate(operations)

    @Bean
    fun initializer(dataSource: DataSource): DataSourceInitializer {
        val initializer = DataSourceInitializer()
        initializer.setDataSource(dataSource)

        val script = ClassPathResource("db_schema.sql")
        val populator = ResourceDatabasePopulator(script)
        initializer.setDatabasePopulator(populator)

        return initializer
    }
}
