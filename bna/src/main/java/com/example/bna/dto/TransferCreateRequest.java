package com.example.bna.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class TransferCreateRequest {
    @NotNull
    private Long beneficiaryId;

    @NotNull @DecimalMin("0.01") @Digits(integer=16, fraction=2)
    private BigDecimal amount;

    @Size(max=255)
    private String description;

    // Getters / Setters
    public Long getBeneficiaryId(){ return beneficiaryId; }
    public void setBeneficiaryId(Long beneficiaryId){ this.beneficiaryId = beneficiaryId; }
    public BigDecimal getAmount(){ return amount; }
    public void setAmount(BigDecimal amount){ this.amount = amount; }
    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description = description; }
}
