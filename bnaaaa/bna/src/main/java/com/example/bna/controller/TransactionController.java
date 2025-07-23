package com.example.bna.controller;

import com.example.bna.model.Transaction;
import com.example.bna.service.TransactionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // pour permettre les requêtes depuis le front
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(
                request.getAccountId(),
                request.getType(),
                request.getAmount(),
                request.getDescription()
        );
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getByAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccount(accountId));
    }

    @Data
    public static class TransactionRequest {
        private Long accountId;
        private String type;
        private Double amount;
        private String description;
    }
}
