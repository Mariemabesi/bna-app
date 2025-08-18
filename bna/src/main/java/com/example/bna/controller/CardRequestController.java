package com.example.bna.controller;

import com.example.bna.dto.CardRequestResponseDTO;
import com.example.bna.dto.CreateCardRequestDTO;
import com.example.bna.service.CardRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card-requests")
@CrossOrigin(origins = {"http://localhost:5173","http://localhost:3000"}, allowCredentials = "true")
public class CardRequestController {
    private final CardRequestService service;

    public CardRequestController(CardRequestService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<CardRequestResponseDTO> create(@Valid @RequestBody CreateCardRequestDTO body) {
        return ResponseEntity.status(201).body(service.create(body));
    }
}
