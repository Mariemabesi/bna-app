package com.example.bna.service;

import com.example.bna.model.User;
import com.example.bna.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Création user avec logs pour débogage
    public User createUser(User user) {
        logger.debug("Avant sauvegarde, user id: {}", user.getId());

        String password = user.getPassword();
        if (password.startsWith("$2a$") || password.startsWith("$2b$") || password.startsWith("$2y$")) {
            logger.debug("Mot de passe détecté en hash bcrypt, on ne le re-hashe pas.");
            User savedUser = userRepository.save(user);
            logger.debug("Après sauvegarde, user id: {}", savedUser.getId());
            return savedUser;
        } else {
            logger.debug("Mot de passe en clair détecté, on le hash avant sauvegarde.");
            user.setPassword(passwordEncoder.encode(password));
            User savedUser = userRepository.save(user);
            logger.debug("Après sauvegarde, user id: {}", savedUser.getId());
            return savedUser;
        }
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
