package com.example.bna.service;

import com.example.bna.model.Card;
import com.example.bna.model.Budget;
import com.example.bna.model.Financials;
import com.example.bna.repository.CardRepository;
import com.example.bna.repository.BudgetRepository;
import com.example.bna.repository.FinancialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final CardRepository cardRepository;
    private final BudgetRepository budgetRepository;
    private final FinancialsRepository financialsRepository;

    @Autowired
    public DashboardService(CardRepository cardRepository, BudgetRepository budgetRepository, FinancialsRepository financialsRepository) {
        this.cardRepository = cardRepository;
        this.budgetRepository = budgetRepository;
        this.financialsRepository = financialsRepository;
    }

    // Fetch all cards for the logged-in user
    public List<Card> getAllCards(Long userId) {
        return cardRepository.findByUserId(userId); // Query cards by user ID
    }

    // Fetch all budgets for the logged-in user
    public List<Budget> getAllBudgets(Long userId) {
        return budgetRepository.findByUserId(userId); // Query budgets by user ID
    }

    // Fetch all financials for the logged-in user
    public List<Financials> getAllFinancials(Long userId) {
        return financialsRepository.findByUserId(userId); // Query financials by user ID
    }

    public List<Map<String, Object>> getPieChartDataForUser(Long userId) {
        List<Budget> budgets = budgetRepository.findByUserId(userId);

        // Aggregate sums by category name in memory
        Map<String, Double> aggregated = budgets.stream()
                .collect(Collectors.groupingBy(
                        b -> b.getCategory(),
                        Collectors.summingDouble(Budget::getAmount)
                ));

        // Transform map to List<Map<String, Object>> for frontend
        return aggregated.entrySet().stream()
                .map(entry -> Map.of(
                        "name", entry.getKey(),
                        "value", (Object) entry.getValue()
                ))
                .collect(Collectors.toList());
    }


}
