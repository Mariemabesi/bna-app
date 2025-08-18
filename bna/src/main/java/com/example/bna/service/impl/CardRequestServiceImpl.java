package com.example.bna.service.impl;

import com.example.bna.dto.CardRequestResponseDTO;
import com.example.bna.dto.CreateCardRequestDTO;
import com.example.bna.model.CardRequest;
import com.example.bna.model.CardRequestStatus;
import com.example.bna.model.CardType;
import com.example.bna.repository.CardRequestRepository;
import com.example.bna.service.CardRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardRequestServiceImpl implements CardRequestService {

    private final CardRequestRepository repository;

    @Override
    public CardRequestResponseDTO create(CreateCardRequestDTO dto) {
        // Normalize incoming type (handles VISA_ALTIUS -> VISA, etc.)
        CardType normalizedType = CardType.fromValue(dto.getCardType());

        CardRequest entity = CardRequest.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .cardType(normalizedType)
                .status(CardRequestStatus.PENDING)
                .build();

        CardRequest saved = repository.save(entity);

        // Convert enums to Strings for the response DTO (fixes your compile error)
        return CardRequestResponseDTO.builder()
                .id(saved.getId())
                .fullName(saved.getFullName())
                .email(saved.getEmail())
                .address(saved.getAddress())
                .cardType(saved.getCardType().name())   // enum -> String
                .status(saved.getStatus().name())       // enum -> String (FIX)
                .createdAt(saved.getCreatedAt())
                .build();
    }
}
