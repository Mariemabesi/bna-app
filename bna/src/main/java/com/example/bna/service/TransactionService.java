package com.example.bna.service;

import com.example.bna.model.Account;
import com.example.bna.model.Transaction;
import com.example.bna.repository.AccountRepository;
import com.example.bna.repository.TransactionRepository;
import com.example.bna.exception.AccountNotFoundException;
import com.example.bna.exception.InsufficientFundsException;
import com.example.bna.exception.InvalidTransactionTypeException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();  // This fetches all transactions from the repository
    }

    // Enum for transaction types
    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

    @Transactional
    public Transaction createTransaction(Long accountId, String type, Double amount, String description) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        TransactionType transactionType;
        try {
            transactionType = TransactionType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidTransactionTypeException("Invalid transaction type");
        }

        switch (transactionType) {
            case DEPOSIT:
                account.setBalance(account.getBalance() + amount);
                break;
            case WITHDRAWAL:
                if (account.getBalance() < amount) {
                    throw new InsufficientFundsException("Insufficient funds");
                }
                account.setBalance(account.getBalance() - amount);
                break;
        }

        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .type(transactionType.name())
                .amount(amount)
                .dateTime(LocalDateTime.now())
                .description(description)
                .build();

        logger.info("Transaction created for account {}: Type = {}, Amount = {}", accountId, type, amount);

        return transactionRepository.save(transaction);
    }

    // Fetch transactions for a specific account, or for all accounts if no specific account is provided
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        if (accountId == null) {
            return transactionRepository.findAll();  // Fetch all transactions if no accountId is provided
        } else {
            return transactionRepository.findByAccountId(accountId);  // Fetch transactions by specific accountId
        }
    }
}

