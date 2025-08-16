package com.example.bna.repository;

import com.example.bna.model.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    boolean existsByCheckNumber(String checkNumber);
}
