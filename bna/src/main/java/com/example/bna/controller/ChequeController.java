package com.example.bna.controller;

import com.example.bna.model.Cheque;
import com.example.bna.service.ChequeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cheques")
@CrossOrigin(origins = "*") // Autoriser le front
public class ChequeController {

    private final ChequeService chequeService;

    public ChequeController(ChequeService chequeService) {
        this.chequeService = chequeService;
    }

    @GetMapping
    public List<Cheque> getAllCheques() {
        return chequeService.getAllCheques();
    }

    @PostMapping
    public ResponseEntity<Cheque> createCheque(@RequestBody Cheque cheque) {
        return ResponseEntity.ok(chequeService.createCheque(cheque));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cheque> updateCheque(@PathVariable Long id, @RequestBody Cheque cheque) {
        return ResponseEntity.ok(chequeService.updateCheque(id, cheque));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheque(@PathVariable Long id) {
        chequeService.deleteCheque(id);
        return ResponseEntity.noContent().build();
    }
}
