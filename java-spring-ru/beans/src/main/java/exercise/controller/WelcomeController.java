package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    private Daytime daytime;

    @GetMapping("/welcome")
    public String welcome() {
        var name = daytime.getName();
        var str = name.equals("night") ? "It is night now! Welcome to Spring!" : "It is day now! Welcome to Spring!";
        return str;
    }
}
// END