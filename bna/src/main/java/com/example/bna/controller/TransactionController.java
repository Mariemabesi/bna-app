package com.example.bna.controller;

import com.example.bna.dto.TransactionDTO;
import com.example.bna.service.TransactionService;
import com.example.bna.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // Fetch transactions for a specific account, or all transactions if no account is provided
    @GetMapping("/account")
    public ResponseEntity<List<TransactionDTO>> getTransactions(
            @RequestParam(required = false) Long accountId,
            @AuthenticationPrincipal UserDetails userDetails) {

        try {
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            // Fetch transactions by accountId
            List<Transaction> transactions = transactionService.getTransactionsByAccount(accountId);

            if (transactions.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            }

            // Convert the list of Transaction entities to TransactionDTOs
            List<TransactionDTO> transactionDTOs = transactions.stream()
                    .map(transaction -> new TransactionDTO(
                            transaction.getId(),
                            transaction.getAmount(),
                            transaction.getDateTime().toString(),
                            transaction.getDescription(),
                            transaction.getType(),
                            transaction.getAccount().getId()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(transactionDTOs);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
