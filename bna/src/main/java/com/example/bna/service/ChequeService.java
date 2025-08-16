package com.example.bna.service;

import com.example.bna.model.Cheque;
import com.example.bna.repository.ChequeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChequeService {

    private final ChequeRepository chequeRepository;

    public ChequeService(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    public List<Cheque> getAllCheques() {
        return chequeRepository.findAll();
    }

    public Cheque createCheque(Cheque cheque) {
        if (chequeRepository.existsByCheckNumber(cheque.getCheckNumber())) {
            throw new RuntimeException("Ce numéro de chèque existe déjà");
        }
        return chequeRepository.save(cheque);
    }

    public Cheque updateCheque(Long id, Cheque updatedCheque) {
        Cheque cheque = chequeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chèque introuvable"));
        cheque.setAmount(updatedCheque.getAmount());
        cheque.setDate(updatedCheque.getDate());
        cheque.setRecipient(updatedCheque.getRecipient());
        cheque.setStatus(updatedCheque.getStatus());
        cheque.setImage(updatedCheque.getImage());
        cheque.setLinks(updatedCheque.getLinks());
        return chequeRepository.save(cheque);
    }

    public void deleteCheque(Long id) {
        chequeRepository.deleteById(id);
    }
}
