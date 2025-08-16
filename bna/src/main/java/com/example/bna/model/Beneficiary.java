package com.example.bna.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "beneficiaries",
        uniqueConstraints = @UniqueConstraint(name="uk_beneficiary_iban", columnNames = "accountIban"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=80)
    private String firstName;   // Prénom

    @Column(nullable=false, length=80)
    private String lastName;    // Nom

    @Column(nullable=false, length=34)
    private String accountIban; // Compte (IBAN/RIB)

    @Column(nullable=false, length=120)
    private String bankName;    // Banque

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private BeneficiaryType type; // INTERNE / EXTERNE / INTERNATIONAL

    @Column(nullable=false, updatable=false)
    @Builder.Default
    private Instant createdAt = Instant.now();
}
