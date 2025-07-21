package com.microservice.BankingApplication.controllers;

import com.microservice.BankingApplication.entity.Loan;
import com.microservice.BankingApplication.services.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Slf4j
@Validated
public class LoanController {

    private final LoanService loanService;

    /**
     * Get loan by ID
     * GET /api/loans/{loanId}
     */
    @GetMapping("/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable @NotBlank String loanId) {
        log.info("REST request to get loan with ID: {}", loanId);
        Loan loan = loanService.getLoanById(loanId);
        return ResponseEntity.ok(loan);
    }

    /**
     * Get all loans
     * GET /api/loans
     */
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        log.info("REST request to get all loans");
        List<Loan> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    /**
     * Get loans by customer ID
     * GET /api/loans/customer/{customerId}
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Loan>> getLoansByCustomerId(
            @PathVariable @NotBlank String customerId) {
        log.info("REST request to get loans for customer ID: {}", customerId);
        List<Loan> loans = loanService.getLoansByCustomerId(customerId);
        return ResponseEntity.ok(loans);
    }

    /**
     * Get loans by loan type
     * GET /api/loans/type/{loanType}
     */
    @GetMapping("/type/{loanType}")
    public ResponseEntity<List<Loan>> getLoansByType(
            @PathVariable @NotBlank String loanType) {
        log.info("REST request to get loans of type: {}", loanType);
        List<Loan> loans = loanService.getLoansByType(loanType);
        return ResponseEntity.ok(loans);
    }

    /**
     * Get loans by interest rate range
     * GET /api/loans/interest-rate?minRate=5.0&maxRate=10.0
     */
    @GetMapping("/interest-rate")
    public ResponseEntity<List<Loan>> getLoansByInterestRateRange(
            @RequestParam double minRate,
            @RequestParam double maxRate) {
        log.info("REST request to get loans with interest rate between {} and {}", minRate, maxRate);
        List<Loan> loans = loanService.getLoansByInterestRateRange(minRate, maxRate);
        return ResponseEntity.ok(loans);
    }

    /**
     * Get loans by customer email
     * GET /api/loans/customer/email/{email}
     */
    @GetMapping("/customer/email/{email}")
    public ResponseEntity<List<Loan>> getLoansByCustomerEmail(
            @PathVariable @Email String email) {
        log.info("REST request to get loans for customer email: {}", email);
        List<Loan> loans = loanService.getLoansByCustomerEmail(email);
        return ResponseEntity.ok(loans);
    }

    /**
     * Create a new loan
     * POST /api/loans
     */
    @PostMapping
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody Loan loan) {
        log.info("REST request to create new loan with ID: {}", loan.getLoanId());
        Loan createdLoan = loanService.createLoan(loan);
        return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
    }

    /**
     * Update an existing loan
     * PUT /api/loans/{loanId}
     */
    @PutMapping("/{loanId}")
    public ResponseEntity<Loan> updateLoan(
            @PathVariable @NotBlank String loanId,
            @Valid @RequestBody Loan loanDetails) {
        log.info("REST request to update loan with ID: {}", loanId);
        Loan updatedLoan = loanService.updateLoan(loanId, loanDetails);
        return ResponseEntity.ok(updatedLoan);
    }

    /**
     * Delete a loan
     * DELETE /api/loans/{loanId}
     */
    @DeleteMapping("/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable @NotBlank String loanId) {
        log.info("REST request to delete loan with ID: {}", loanId);
        loanService.deleteLoan(loanId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Check if loan exists for customer
     * GET /api/loans/{loanId}/customer/{customerId}/exists
     */
    @GetMapping("/{loanId}/customer/{customerId}/exists")
    public ResponseEntity<Boolean> existsByLoanIdAndCustomerId(
            @PathVariable @NotBlank String loanId,
            @PathVariable @NotBlank String customerId) {
        log.info("REST request to check if loan {} exists for customer {}", loanId, customerId);
        boolean exists = loanService.existsByLoanIdAndCustomerId(loanId, customerId);
        return ResponseEntity.ok(exists);
    }
}