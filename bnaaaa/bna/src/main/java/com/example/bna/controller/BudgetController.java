package com.example.bna.controller;

import com.example.bna.model.Budget;
import com.example.bna.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<Budget> create(@RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.createBudget(budget));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Budget>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(budgetService.getBudgetsByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> update(@PathVariable Long id, @RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.updateBudget(id, budget));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
}
