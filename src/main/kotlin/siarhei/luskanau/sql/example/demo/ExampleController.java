package siarhei.luskanau.sql.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class ExampleController {

    @GetMapping(path = "/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping(path = "/greet/{name}")
    public String greetUser(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @PostMapping(path = "/echo")
    public String echoMessage(@RequestBody String message) {
        return "You said: " + message;
    }
}
