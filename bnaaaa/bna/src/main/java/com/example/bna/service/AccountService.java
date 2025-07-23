package com.example.bna.service;

import com.example.bna.model.Account;
import com.example.bna.model.User;
import com.example.bna.repository.AccountRepository;
import com.example.bna.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Account> getAccountsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return accountRepository.findByUser(user);
    }

    public Account createAccount(String username, Account account) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        account.setUser(user);
        return accountRepository.save(account);
    }
}
