package siarhei.luskanau.sql.example.demo.scheduler

import org.springframework.stereotype.Service

@Service
class SchedulerAction {
    fun execute() {
        println("######## SchedulerAction.executed ########")
    }
}
