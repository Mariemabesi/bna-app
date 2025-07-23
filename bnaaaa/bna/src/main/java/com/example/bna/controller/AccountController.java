package com.example.bna.controller;

import com.example.bna.model.Account;
import com.example.bna.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // ✅ Get all accounts for a username
    @GetMapping("/{username}")
    public List<Account> getAccountsByUsername(@PathVariable String username) {
        return accountService.getAccountsByUsername(username);
    }

    // ✅ Create a new account for a user
    @PostMapping("/{username}")
    public Account createAccount(@PathVariable String username, @RequestBody Account account) {
        return accountService.createAccount(username, account);
    }
}
