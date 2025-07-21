package com.microservice.BankingApplication.services;

import com.microservice.BankingApplication.entity.Account;
import com.microservice.BankingApplication.exceptions.ResourceNotFoundException;
import com.microservice.BankingApplication.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }


    public Account getById(String number) {
//        System.out.println(repo.findById(number));
        return repo.findById(number)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account with id %s not found".formatted(number)));
    }
}