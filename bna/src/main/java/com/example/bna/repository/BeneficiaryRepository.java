package com.example.bna.repository;

import com.example.bna.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    Optional<Beneficiary> findByAccountIban(String accountIban);
}
