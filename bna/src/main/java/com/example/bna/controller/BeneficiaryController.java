package com.example.bna.controller;

import com.example.bna.dto.BeneficiaryCreateRequest;
import com.example.bna.model.Beneficiary;
import com.example.bna.service.BeneficiaryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beneficiaries")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class BeneficiaryController {

    private final BeneficiaryService service;

    public BeneficiaryController(BeneficiaryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Beneficiary> create(@Valid @RequestBody BeneficiaryCreateRequest body) {
        return ResponseEntity.ok(service.create(body));
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.list());
    }
}
