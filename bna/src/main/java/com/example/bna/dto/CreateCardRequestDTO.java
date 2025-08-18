package com.example.bna.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateCardRequestDTO {
    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String address;

    /**
     * Accepts values like "VISA", "MASTERCARD", "AMEX", "OTHER",
     * or product-like values such as "VISA_ALTIUS", "MASTERCARD_CIBT".
     */
    @NotBlank
    private String cardType;
}
