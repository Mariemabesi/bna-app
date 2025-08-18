package com.example.bna.dto;

import lombok.*;
import java.time.OffsetDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CardRequestResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private String address;
    private String cardType;   // Enum name as String (e.g., "VISA")
    private String status;     // Enum name as String (e.g., "PENDING")
    private OffsetDateTime createdAt;
}
