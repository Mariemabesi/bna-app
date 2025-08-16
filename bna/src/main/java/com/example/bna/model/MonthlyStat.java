package com.example.bna.model;

import jakarta.persistence.*;

@Entity
@Table(name = "monthly_stats")
public class MonthlyStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;

    private Double revenus;

    private Double depenses;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public Double getRevenus() { return revenus; }
    public void setRevenus(Double revenus) { this.revenus = revenus; }

    public Double getDepenses() { return depenses; }
    public void setDepenses(Double depenses) { this.depenses = depenses; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
