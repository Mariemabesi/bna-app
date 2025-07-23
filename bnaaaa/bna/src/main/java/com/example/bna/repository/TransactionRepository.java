package com.example.bna.repository;

import com.example.bna.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Récupérer les transactions d’un compte donné
    List<Transaction> findByAccountId(Long accountId);
}
