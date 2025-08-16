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

    // Relation vers le compte (Account)
    @ManyToOne(fetch = FetchType.LAZY) // Use FetchType.LAZY for performance optimization
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Type de transaction : DEPOSIT ou WITHDRAWAL
    @Column(nullable = false)
    private String type;

    // Amount for the transaction (Deposit or Withdrawal)
    @Column(nullable = false)
    private Double amount;

    // Date and time when the transaction occurred
    @Column(nullable = false)
    private LocalDateTime dateTime;

    // Optional description for the transaction
    private String description;  // This can be nullable depending on the requirements
}
