package com.example.bna.service;

import com.example.bna.dto.CardRequestResponseDTO;
import com.example.bna.dto.CreateCardRequestDTO;

public interface CardRequestService {
    CardRequestResponseDTO create(CreateCardRequestDTO dto);
}
