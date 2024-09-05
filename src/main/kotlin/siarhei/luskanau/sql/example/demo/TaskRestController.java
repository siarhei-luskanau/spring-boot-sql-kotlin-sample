package siarhei.luskanau.sql.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskRestController {

    @GetMapping(path = "/task1")
    List<String> task1() {
        return List.of("task123");
    }
}
