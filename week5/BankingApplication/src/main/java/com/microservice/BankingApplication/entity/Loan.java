package com.microservice.BankingApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Loan {

    @Id
    private String loanId;

    private String loanType;

    private double loanAmount;

    private double interestRate;

    private LocalDate startDate;

    private int durationMonths;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}