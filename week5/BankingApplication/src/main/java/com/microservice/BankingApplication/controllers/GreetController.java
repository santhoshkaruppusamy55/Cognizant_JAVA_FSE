package com.microservice.BankingApplication.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class GreetController {

    @GetMapping("/home")
    public String greet() {
        return "Welcome to NetBanking! We're glad to have you.";
    }

}