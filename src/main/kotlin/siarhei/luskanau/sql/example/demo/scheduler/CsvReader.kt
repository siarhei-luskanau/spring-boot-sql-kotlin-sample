package siarhei.luskanau.sql.example.demo.scheduler

import org.apache.commons.csv.CSVFormat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.InputStreamReader

@Service
class CsvReader(
    @Autowired private val csvParser: CsvParser,
) {
    fun read(path: String): List<CsvModel> =
        Thread
            .currentThread()
            .contextClassLoader
            .getResourceAsStream(path)
            ?.use { inputStream ->
                InputStreamReader(inputStream).use { reader ->
                    CSVFormat.RFC4180
                        .builder()
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .get()
                        .parse(reader)
                        ?.toList()
                        ?.map {
                            csvParser.parse(
                                time = requireNotNull(it.get("time")).trim(),
                                bitmask = requireNotNull(it.get("bitmask")).trim(),
                            )
                        }
                }
            } ?: throw CsvFileException(path)
}
