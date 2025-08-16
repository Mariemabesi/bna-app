package com.example.bna.service;

import com.example.bna.model.Account;
import com.example.bna.model.User;
import com.example.bna.repository.AccountRepository;
import com.example.bna.repository.UserRepository;
import com.example.bna.exception.UserNotFoundException;  // Import the custom exception
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Fetches accounts associated with a given username.
     *
     * @param username the username of the user
     * @return a list of accounts associated with the username
     * @throws UserNotFoundException if the user is not found
     */
    public List<Account> getAccountsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return accountRepository.findByUser(user);  // Fetch accounts for this user
    }

    /**
     * Creates a new account and associates it with the specified user.
     *
     * @param username the username of the user
     * @param account the account to create
     * @return the created account
     * @throws UserNotFoundException if the user is not found
     */
    @Transactional
    public Account createAccount(String username, Account account) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        account.setUser(user);  // Associate the account with the user
        return accountRepository.save(account);
    }
}
