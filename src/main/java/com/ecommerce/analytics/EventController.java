package com.ecommerce.analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository repo;

    // 🔹 1. ADD EVENT (POST)
    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event) {
        event.setTimestamp(LocalDateTime.now());
        return repo.save(event);
    }

    // 🔹 2. GET ALL EVENTS
    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return repo.findAll();
    }

    // 🔹 3. TOTAL REVENUE
    @GetMapping("/revenue")
    public Double getRevenue() {
        return repo.getTotalRevenue();
    }

    // 🔹 4. TOP PRODUCTS
    @GetMapping("/top-products")
    public List<Object[]> topProducts() {
        return repo.getTopProducts();
    }

    // 🔹 5. CITY-WISE REVENUE
    @GetMapping("/city-revenue")
    public List<Object[]> cityRevenue() {
        return repo.getRevenueByCity();
    }

    // 🔹 6. TOP CITIES (MOST TRANSACTIONS)
    @GetMapping("/top-cities")
    public List<Object[]> topCities() {
        return repo.topCities();
    }

    // 🔹 7. FAILED PAYMENTS
    @GetMapping("/failed")
    public Long failedPayments() {
        return repo.failedPayments();
    }

    // 🔹 8. HIGH VALUE TRANSACTIONS (Fraud Detection)
    @GetMapping("/high-value")
    public List<Event> highTransactions() {
        return repo.highTransactions();
    }
}