package com.example.bna.service;

import com.example.bna.model.User;
import com.example.bna.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // (Optional) for admin use: create user with encoded password
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ now by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
