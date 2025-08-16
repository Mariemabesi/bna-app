package com.example.bna.repository;

import com.example.bna.model.Financials;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FinancialsRepository extends JpaRepository<Financials, Long> {
    List<Financials> findByUserId(Long userId);  // Custom query to find financials by user_id
}
