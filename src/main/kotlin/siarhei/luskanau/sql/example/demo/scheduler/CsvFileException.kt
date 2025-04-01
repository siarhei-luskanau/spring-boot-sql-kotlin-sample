package siarhei.luskanau.sql.example.demo.scheduler

class CsvFileException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}
