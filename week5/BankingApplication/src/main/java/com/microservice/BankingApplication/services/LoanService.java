package com.microservice.BankingApplication.services;


import com.microservice.BankingApplication.entity.Loan;
import com.microservice.BankingApplication.repository.LoanRepository;
import com.microservice.BankingApplication.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LoanService {

    private final LoanRepository loanRepository;

    /**
     * Get loan by loan ID
     */
    public Loan getLoanById(String loanId) {
        log.info("Fetching loan with ID: {}", loanId);
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Loan not found with ID: " + loanId));
    }

    /**
     * Get all loans by customer ID
     */
    public List<Loan> getLoansByCustomerId(String customerId) {
        log.info("Fetching loans for customer ID: {}", customerId);
        List<Loan> loans = loanRepository.findByCustomerCustomerId(customerId);
        if (loans.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No loans found for customer ID: " + customerId);
        }
        return loans;
    }

    /**
     * Get all loans
     */
    public List<Loan> getAllLoans() {
        log.info("Fetching all loans");
        return loanRepository.findAll();
    }

    /**
     * Get loans by loan type
     */
    public List<Loan> getLoansByType(String loanType) {
        log.info("Fetching loans of type: {}", loanType);
        return loanRepository.findByLoanType(loanType);
    }

    /**
     * Get loans by interest rate range
     */
    public List<Loan> getLoansByInterestRateRange(double minRate, double maxRate) {
        log.info("Fetching loans with interest rate between {} and {}", minRate, maxRate);
        return loanRepository.findByInterestRateBetween(minRate, maxRate);
    }

    /**
     * Get loans by customer email
     */
    public List<Loan> getLoansByCustomerEmail(String email) {
        log.info("Fetching loans for customer email: {}", email);
        return loanRepository.findLoansByCustomerEmail(email);
    }

    /**
     * Create a new loan
     */
    @Transactional
    public Loan createLoan(Loan loan) {
        log.info("Creating new loan with ID: {}", loan.getLoanId());
        return loanRepository.save(loan);
    }

    /**
     * Update an existing loan
     */
    @Transactional
    public Loan updateLoan(String loanId, Loan loanDetails) {
        log.info("Updating loan with ID: {}", loanId);

        Loan existingLoan = getLoanById(loanId);

        existingLoan.setLoanType(loanDetails.getLoanType());
        existingLoan.setLoanAmount(loanDetails.getLoanAmount());
        existingLoan.setInterestRate(loanDetails.getInterestRate());
        existingLoan.setStartDate(loanDetails.getStartDate());
        existingLoan.setDurationMonths(loanDetails.getDurationMonths());

        return loanRepository.save(existingLoan);
    }

    /**
     * Delete loan by ID
     */
    @Transactional
    public void deleteLoan(String loanId) {
        log.info("Deleting loan with ID: {}", loanId);

        if (!loanRepository.existsById(loanId)) {
            throw new ResourceNotFoundException("Loan not found with ID: " + loanId);
        }

        loanRepository.deleteById(loanId);
    }

    /**
     * Check if loan exists for a specific customer
     */
    public boolean existsByLoanIdAndCustomerId(String loanId, String customerId) {
        return loanRepository.existsByLoanIdAndCustomerCustomerId(loanId, customerId);
    }
}