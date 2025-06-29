package com.Mockito.handsOn.repository;


import com.Mockito.handsOn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Optional: custom methods (e.g., findByName)
    User findById(String name);
}