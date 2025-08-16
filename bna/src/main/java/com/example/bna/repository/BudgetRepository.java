package com.example.bna.repository;

import com.example.bna.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserId(Long userId);  // Custom query to find budgets by user_id
}
