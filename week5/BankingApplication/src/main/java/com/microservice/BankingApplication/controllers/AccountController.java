package com.microservice.BankingApplication.controllers;

import com.microservice.BankingApplication.entity.Account;
import com.microservice.BankingApplication.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    /**
     * GET /accounts/{number}
     * Example: GET <a href="http://localhost:8081/accounts/00987987973432">...</a>
     */
    @GetMapping("/{number}")
    public ResponseEntity<Account> findAccount(@PathVariable String number) {
        Account account = service.getById(number);
        return ResponseEntity.ok(account);
    }
}