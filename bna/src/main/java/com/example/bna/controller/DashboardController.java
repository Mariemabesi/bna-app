package com.example.bna.controller;

import com.example.bna.model.Budget;
import com.example.bna.model.Card;
import com.example.bna.model.Financials;
import com.example.bna.model.MonthlyStat;
import com.example.bna.model.User;
import com.example.bna.repository.CardRepository;
import com.example.bna.repository.UserRepository;
import com.example.bna.service.DashboardService;
import com.example.bna.service.MonthlyStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;
    private final UserRepository userRepository;
    private final MonthlyStatService monthlyStatService;
    private final CardRepository cardRepository;

    @Autowired
    public DashboardController(DashboardService dashboardService,
                               UserRepository userRepository,
                               MonthlyStatService monthlyStatService,
                               CardRepository cardRepository) {
        this.dashboardService = dashboardService;
        this.userRepository = userRepository;
        this.monthlyStatService = monthlyStatService;
        this.cardRepository = cardRepository;
    }

    // Utility method to get current logged-in username from Spring Security context
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            return (String) principal;
        } else {
            throw new RuntimeException("Cannot get username from security context");
        }
    }

    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getCards() {
        String username = getCurrentUsername(); // gets username from Spring Security token
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        List<Card> cards = dashboardService.getAllCards(user.getId());
        return ResponseEntity.ok(cards);
    }


    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        card.setUser(user);

        Card savedCard = cardRepository.save(card);
        return ResponseEntity.ok(savedCard);
    }

    @GetMapping("/budget")
    public ResponseEntity<List<Budget>> getBudget() {
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        List<Budget> budgets = dashboardService.getAllBudgets(user.getId());
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/financials")
    public ResponseEntity<List<Financials>> getFinancials() {
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        List<Financials> financials = dashboardService.getAllFinancials(user.getId());
        return ResponseEntity.ok(financials);
    }

    @GetMapping("/monthly-stats")
    public ResponseEntity<List<MonthlyStat>> getMonthlyStats() {
        List<MonthlyStat> monthlyStats = monthlyStatService.getAllStats();
        return ResponseEntity.ok(monthlyStats);
    }

    @GetMapping("/pie-data")
    public ResponseEntity<List<Map<String, Object>>> getPieChartData() {
        String username = getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        List<Map<String, Object>> pieData = dashboardService.getPieChartDataForUser(user.getId());
        return ResponseEntity.ok(pieData);
    }
}
