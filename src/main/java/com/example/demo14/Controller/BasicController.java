package com.example.demo14.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class BasicController {

    @GetMapping("")
    //localhost:8080/welcome?user=Gianni
    public String welcome(@RequestParam String user){
        String message = "Welcome " + user;
        return message;
    }
}
