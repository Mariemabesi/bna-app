package com.example.bna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDTO {

    private Long id;            // Transaction ID
    private Double amount;      // Transaction amount
    private String dateTime;    // Date and Time of the transaction
    private String description; // Transaction description
    private String type;        // Transaction type (e.g., DEPOSIT or WITHDRAWAL)
    private Long accountId;     // Account ID
}
