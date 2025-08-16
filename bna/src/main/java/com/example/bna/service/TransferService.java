package com.example.bna.service;

import com.example.bna.dto.TransferCreateRequest;
import com.example.bna.model.*;
import com.example.bna.repository.BeneficiaryRepository;
import com.example.bna.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TransferService {
    private final TransferRepository transferRepo;
    private final BeneficiaryRepository beneficiaryRepo;

    public TransferService(TransferRepository transferRepo, BeneficiaryRepository beneficiaryRepo) {
        this.transferRepo = transferRepo;
        this.beneficiaryRepo = beneficiaryRepo;
    }

    @Transactional
    public Transfer create(TransferCreateRequest req) {
        Beneficiary b = beneficiaryRepo.findById(req.getBeneficiaryId())
                .orElseThrow(() -> new IllegalArgumentException("Bénéficiaire introuvable"));

        Transfer t = Transfer.builder()
                .beneficiary(b)
                .amount(req.getAmount())
                .description(req.getDescription())
                .status(TransferStatus.SUCCESS) // ici tu peux mettre PENDING si tu veux un traitement différé
                .build();

        return transferRepo.save(t);
    }

    public List<Transfer> list() {
        return transferRepo.findAll();
    }
}
