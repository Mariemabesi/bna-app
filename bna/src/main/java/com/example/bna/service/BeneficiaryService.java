package com.example.bna.service;

import com.example.bna.dto.BeneficiaryCreateRequest;
import com.example.bna.model.Beneficiary;
import com.example.bna.repository.BeneficiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {
    private final BeneficiaryRepository repo;
    public BeneficiaryService(BeneficiaryRepository repo){ this.repo = repo; }

    public Beneficiary create(BeneficiaryCreateRequest req){
        repo.findByAccountIban(req.getAccountIban()).ifPresent(b -> {
            throw new IllegalArgumentException("Ce compte (IBAN/RIB) est déjà enregistré.");
        });
        Beneficiary b = new Beneficiary();
        b.setFirstName(req.getFirstName());
        b.setLastName(req.getLastName());
        b.setAccountIban(req.getAccountIban());
        b.setBankName(req.getBankName());
        b.setType(req.getType());
        return repo.save(b);
    }

    public List<Beneficiary> list(){ return repo.findAll(); }
}
