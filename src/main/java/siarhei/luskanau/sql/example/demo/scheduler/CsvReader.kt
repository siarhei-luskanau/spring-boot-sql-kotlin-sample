package siarhei.luskanau.sql.example.demo.scheduler

import org.apache.commons.csv.CSVFormat
import org.springframework.stereotype.Service
import java.io.InputStreamReader

@Service
class CsvReader {

    fun read(): List<ScvModel> =
        Thread.currentThread().contextClassLoader
            .getResourceAsStream("scheduler.csv")?.use { inputStream ->
                InputStreamReader(inputStream).use { reader ->
                    CSVFormat.RFC4180.builder().setHeader().setSkipHeaderRecord(true)
                        .build().parse(reader)?.toList()?.map {
                            ScvModel(
                                time = requireNotNull(it.get("time")).trimIndent(),
                                bitmask = requireNotNull(it.get("bitmask")).trimIndent()
                            )
                        }
                }
            }.orEmpty()
}