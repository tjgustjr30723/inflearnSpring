package study.data_jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContorller {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
