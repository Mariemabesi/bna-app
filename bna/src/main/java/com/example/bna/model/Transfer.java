package com.example.bna.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="beneficiary_id",
            nullable=false,
            foreignKey=@ForeignKey(name="fk_transfer_beneficiary"))
    private Beneficiary beneficiary;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal amount;

    @Column(length=255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    @Builder.Default
    private TransferStatus status = TransferStatus.PENDING;

    @Column(nullable=false, updatable=false)
    @Builder.Default
    private Instant createdAt = Instant.now();
}
