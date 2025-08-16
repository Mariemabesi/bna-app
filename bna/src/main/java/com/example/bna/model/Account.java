package com.example.bna.model;

import jakarta.persistence.*;
import lombok.Data;  // Import Lombok's @Data annotation

@Data  // Lombok annotation to automatically generate getters, setters, toString(), etc.
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String name;
    private double balance;
    private double availableBalance;

    // Many accounts can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Foreign key column that links to the User
    private User user;  // User that owns this account

    // No need to manually write getters and setters due to @Data, Lombok will generate them
}
