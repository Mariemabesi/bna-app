package com.example.bna.dto;

import com.example.bna.model.BeneficiaryType;
import jakarta.validation.constraints.*;

public class BeneficiaryCreateRequest {
    @NotBlank @Size(max=80)
    private String firstName;

    @NotBlank @Size(max=80)
    private String lastName;

    @NotBlank @Size(min=14, max=34)
    private String accountIban;

    @NotBlank @Size(max=120)
    private String bankName;

    @NotNull
    private BeneficiaryType type;

    // Getters / Setters
    public String getFirstName(){ return firstName; }
    public void setFirstName(String firstName){ this.firstName = firstName; }
    public String getLastName(){ return lastName; }
    public void setLastName(String lastName){ this.lastName = lastName; }
    public String getAccountIban(){ return accountIban; }
    public void setAccountIban(String accountIban){ this.accountIban = accountIban; }
    public String getBankName(){ return bankName; }
    public void setBankName(String bankName){ this.bankName = bankName; }
    public BeneficiaryType getType(){ return type; }
    public void setType(BeneficiaryType type){ this.type = type; }
}
