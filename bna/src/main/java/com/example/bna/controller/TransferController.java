package com.example.bna.controller;

import com.example.bna.dto.TransferCreateRequest;
import com.example.bna.model.Transfer;
import com.example.bna.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Transfer> create(@Valid @RequestBody TransferCreateRequest body) {
        return ResponseEntity.ok(service.create(body));
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(service.list());
    }
}
