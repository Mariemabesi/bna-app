package com.example.bna.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

@Entity
public class Financials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Fixed syntax here
    private Long id;

    @Column(name = "revenus") // Map to French column name in DB
    private Double revenus;

    @Column(name = "depenses")
    private Double depenses;

    @Column(name = "solde_actuel")  // Remove backslash escape
    private Double soldeActuel;

    @ManyToOne
    @JoinColumn(name = "user_id")  // Matches DB foreign key column
    private User user;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRevenus() {
        return revenus;
    }

    public void setRevenus(Double revenus) {
        this.revenus = revenus;
    }

    public Double getDepenses() {
        return depenses;
    }

    public void setDepenses(Double depenses) {
        this.depenses = depenses;
    }

    // Add getter and setter for soldeActuel
    public Double getSoldeActuel() {
        return soldeActuel;
    }

    public void setSoldeActuel(Double soldeActuel) {
        this.soldeActuel = soldeActuel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
