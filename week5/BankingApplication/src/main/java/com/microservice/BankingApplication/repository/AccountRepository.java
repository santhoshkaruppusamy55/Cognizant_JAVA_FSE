package com.microservice.BankingApplication.repository;

import com.microservice.BankingApplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    // extends basic CRUD + paging/sorting
    // additional custom queries (if any) go here
}