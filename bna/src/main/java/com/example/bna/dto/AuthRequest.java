package com.example.bna.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private Long accountId;
    private String username;
    private String password;
}
