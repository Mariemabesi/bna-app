package com.example.bna.repository;

import com.example.bna.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUserId(Long userId);  // Custom query to find cards by user_id
}
