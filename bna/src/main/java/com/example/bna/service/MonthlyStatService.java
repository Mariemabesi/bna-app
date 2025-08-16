package com.example.bna.service;

import com.example.bna.model.MonthlyStat;
import com.example.bna.repository.MonthlyStatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyStatService {

    private final MonthlyStatRepository monthlyStatRepository;

    public MonthlyStatService(MonthlyStatRepository monthlyStatRepository) {
        this.monthlyStatRepository = monthlyStatRepository;
    }

    // This method must be added to fix your error
    public List<MonthlyStat> getAllStats() {
        return monthlyStatRepository.findAll();
    }

    // If you want to filter by user id, add this method (optional)
    /*
    public List<MonthlyStat> getMonthlyStatsByUserId(Long userId) {
        return monthlyStatRepository.findByUserId(userId);
    }
    */
}
