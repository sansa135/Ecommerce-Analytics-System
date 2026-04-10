package com.ecommerce.analytics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final EventRepository repo;

    public DataLoader(EventRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        if (repo.count() > 5) {
            return;
        }

        String[] products = {
                "iPhone 15", "Samsung S23", "Boat Headphones",
                "Nike Shoes", "Laptop Bag", "Smart Watch",
                "Kurti", "Gaming Mouse"
        };

        String[] cities = {
                "Bhopal", "Delhi", "Mumbai", "Pune", "Indore", "Hyderabad"
        };

        String[] methods = {
                "UPI", "Card", "COD", "NetBanking"
        };

        String[] eventTypes = {
                "payment_success", "payment_failed"
        };

        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            Event e = new Event();
            e.setEventType(eventTypes[random.nextInt(eventTypes.length)]);
            e.setProductName(products[random.nextInt(products.length)]);
            e.setAmount(1000 + random.nextInt(90000));
            e.setCity(cities[random.nextInt(cities.length)]);
            e.setPaymentMethod(methods[random.nextInt(methods.length)]);
            e.setTimestamp(LocalDateTime.now().minusDays(random.nextInt(30)));

            repo.save(e);
        }

        System.out.println("50 sample records inserted successfully.");
    }
}