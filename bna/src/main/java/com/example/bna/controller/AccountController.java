package com.example.bna.controller;

import com.example.bna.model.Account;
import com.example.bna.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor  // Lombok for constructor injection
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/me")
    public ResponseEntity<?> getAccountsByUsername(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            // Authentication not found or not valid
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String username = authentication.getName();

        List<Account> accounts = accountService.getAccountsByUsername(username);

        if (accounts.isEmpty()) {
            // Return 404 if no accounts found for the authenticated user
            return ResponseEntity.status(404).body("No accounts found");
        }

        // Return accounts if found
        return ResponseEntity.ok(accounts);
    }

}