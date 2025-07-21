package com.microservice.BankingApplication.repository;


import com.microservice.BankingApplication.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {

    // Find loans by customer ID
    List<Loan> findByCustomerCustomerId(String customerId);

    // Find loans by loan type
    List<Loan> findByLoanType(String loanType);

    // Find loans by interest rate range
    List<Loan> findByInterestRateBetween(double minRate, double maxRate);

    // Custom query to find loans by customer email
    @Query("SELECT l FROM Loan l WHERE l.customer.email = :email")
    List<Loan> findLoansByCustomerEmail(@Param("email") String email);

    // Check if loan exists by loan ID and customer ID
    boolean existsByLoanIdAndCustomerCustomerId(String loanId, String customerId);
}