package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    public String greetings(@RequestParam(defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }
}
// END
