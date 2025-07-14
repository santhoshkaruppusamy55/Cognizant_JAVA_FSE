package com.cognizant.spring_learn.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to the Spring Boot application! Let's build something amazing.";
    }

}