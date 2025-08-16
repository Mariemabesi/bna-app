package com.example.bna.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cheques")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String checkNumber;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 100)
    private String recipient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ChequeStatus status;

    @Column(length = 255)
    private String image;

    @ElementCollection
    @CollectionTable(name = "cheque_links", joinColumns = @JoinColumn(name = "cheque_id"))
    @Column(name = "link", nullable = false)
    @Builder.Default
    private List<String> links = new ArrayList<>();


}
