package com.microservice.BankingApplication.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}