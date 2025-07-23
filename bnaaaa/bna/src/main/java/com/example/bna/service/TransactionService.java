package com.example.bna.service;

import com.example.bna.model.Account;
import com.example.bna.model.Transaction;
import com.example.bna.repository.AccountRepository;
import com.example.bna.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public Transaction createTransaction(Long accountId, String type, Double amount, String description) {
        // Vérifier que le compte existe
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Mettre à jour le solde
        if (type.equalsIgnoreCase("DEPOSIT")) {
            account.setBalance(account.getBalance() + amount);
        } else if (type.equalsIgnoreCase("WITHDRAWAL")) {
            if (account.getBalance() < amount) {
                throw new RuntimeException("Insufficient funds");
            }
            account.setBalance(account.getBalance() - amount);
        } else {
            throw new RuntimeException("Invalid transaction type");
        }

        // Sauvegarder le compte après modification du solde
        accountRepository.save(account);

        // Créer et sauvegarder la transaction
        Transaction transaction = Transaction.builder()
                .account(account)
                .type(type.toUpperCase())
                .amount(amount)
                .dateTime(LocalDateTime.now())
                .description(description)
                .build();

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
