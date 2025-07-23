package com.example.bna.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation vers le compte
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Type de transaction : DEPOSIT ou WITHDRAWAL
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private String description;
}
