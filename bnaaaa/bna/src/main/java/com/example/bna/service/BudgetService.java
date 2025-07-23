package com.example.bna.service;

import com.example.bna.model.Budget;
import com.example.bna.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public Budget createBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public List<Budget> getBudgetsByUser(Long userId) {
        return budgetRepository.findByUserId(userId);
    }

    public Budget updateBudget(Long id, Budget updatedBudget) {
        Budget existing = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        existing.setCategory(updatedBudget.getCategory());
        existing.setLimitAmount(updatedBudget.getLimitAmount());
        return budgetRepository.save(existing);
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
}
